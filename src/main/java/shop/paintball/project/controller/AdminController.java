package shop.paintball.project.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.paintball.project.controller.constant.EndpointConstants;
import shop.paintball.project.controller.constant.EntityConstants;
import shop.paintball.project.controller.constant.ErrorMessageConstantsController;
import shop.paintball.project.controller.constant.MessagePropertiesConstants;
import shop.paintball.project.entity.*;
import shop.paintball.project.exception.ControllerException;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.CategoriesService;
import shop.paintball.project.service.OrderService;
import shop.paintball.project.service.ProductService;
import shop.paintball.project.service.ReviewsService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewsService reviewsService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @RequestMapping("/showOrderProcessing")
    public String showOrderProcessing(@RequestParam(value = EntityConstants.CONSTANTS_ENTITY_MESSAGE, required = false)
                                      String message,
                                      Model theModel) {

        try {

            List<Order> orders = orderService.outputOfOrdersForProcessing();

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_ORDERS, orders.stream()
                    .sorted(Comparator.comparing(Order::getDatePost))
                    .collect(Collectors.toList()));
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PROCESSING_ORDER, new OrderShipping());
            theModel.addAttribute(MessagePropertiesConstants.CONSTANTS_MESSAGE_SUCCESSFUL, message);

            return EndpointConstants.CONSTANTS_PAGE_ORDER_PROCESSING;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_ORDER_FOR_PROCESSING, e);

        }

    }

    @RequestMapping("/showOrderSending")
    public String showOrderSending(@RequestParam(value = EntityConstants.CONSTANTS_ENTITY_MESSAGE, required = false)
                                   String message,
                                   Model theModel) {

        try {

            List<Order> orders = orderService.outputOfOrdersForSending();

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_ORDERS, orders.stream()
                    .sorted(Comparator.comparing(Order::getDatePost))
                    .collect(Collectors.toList()));

            theModel.addAttribute(MessagePropertiesConstants.CONSTANTS_MESSAGE_SUCCESSFUL, message);

            return EndpointConstants.CONSTANTS_PAGE_ORDER_SENDING;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_ORDER_FOR_PROCESSING, e);

        }

    }

    @RequestMapping("/showOrderReady")
    public String showOrderReady(Model theModel) {

        try {

            List<Order> orders = orderService.outputOfOrdersForReady();

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_ORDERS, orders.stream()
                    .sorted(Comparator.comparing(Order::getDatePost))
                    .collect(Collectors.toList()));

            return EndpointConstants.CONSTANTS_PAGE_ORDER_READY;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_ORDER_FOR_READY, e);

        }

    }

    @RequestMapping("/addCategories")
    public String addCategories(@Valid @ModelAttribute(EntityConstants.CONSTANTS_ENTITY_ADD_CATEGORIES)
                                Categories categories,
                                BindingResult theBindingResult, Model theModel, Locale locale) {

        try {

            String message;

            if (theBindingResult.hasErrors()) {

                message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_SAVE_CATEGORIES,
                        null, locale);

                return EndpointConstants.CONSTANTS_REDIRECT_CATEGORIES +
                        "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

            }

            categories.setStatus(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ACTIVE);

            categoriesService.saveCategories(categories);

            message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_SAVE_CATEGORIES,
                    null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_CATEGORIES +
                    "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ADD_CATEGORIES, e);

        }

    }

    @RequestMapping("/processingOrder")
    public String processingOrder(@Valid @ModelAttribute(EntityConstants.CONSTANTS_ENTITY_PROCESSING_ORDER)
                                  OrderShipping orderShipping,
                                  BindingResult theBindingResult, Model theModel,
                                  @ModelAttribute(EntityConstants.CONSTANTS_ENTITY_ORDER_ID) int idOrder,
                                  Locale locale) {

        try {

            String message;

            if (theBindingResult.hasErrors()) {

                message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_SAVE_ORDER_SHIPPING,
                        null, locale);

                return EndpointConstants.CONSTANTS_REDIRECT_ORDER_PROCESSING +
                        "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

            }

            orderShipping.setOrder(orderService.findOrder(idOrder));

            orderService.updateOrderStatus(idOrder, EntityConstants.CONSTANTS_ENTITY_ORDER_STATUS_GETTING);

            orderService.saveOrderShipping(orderShipping);

            message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_SAVE_ORDER_SHIPPING,
                    null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_ORDER_PROCESSING +
                    "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SAVE_ORDER_SHIPPING, e);

        }

    }

    @RequestMapping("/sendingOrder")
    public String sendingOrder(@ModelAttribute(EntityConstants.CONSTANTS_ENTITY_TRACKING_INDEX_ORDER) String trackingIndex,
                               Model theModel,
                               @ModelAttribute(EntityConstants.CONSTANTS_ENTITY_ORDER_ID) int idOrder,
                               Locale locale) {

        try {

            String message;

            if (trackingIndex == null) {

                message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_SAVE_ORDER_SENDING,
                        null, locale);

                return EndpointConstants.CONSTANTS_REDIRECT_ORDER_SENDING +
                        "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

            }

            orderService.saveOrderSending(idOrder, trackingIndex);

            message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_SAVE_ORDER_SENDING,
                    null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_ORDER_SENDING +
                    "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SAVE_ORDER_SHIPPING, e);

        }

    }

    @RequestMapping("/searchOrderReadyList")
    public String searchOrderReady(@RequestParam(value = EntityConstants.CONSTANTS_ENTITY_CATEGORIES_QUERY, required = false)
                                   String query, Model theModel) {

        try {

            List<Order> orders;

            if ((query == null) || (!query.matches("\\d+"))) {

                orders = orderService.outputOfOrdersForReady();

            } else {

                orders = orderService.searchOrderReadyList(query);

            }

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_ORDERS, orders.stream()
                    .sorted(Comparator.comparing(Order::getDatePost))
                    .collect(Collectors.toList()));

            return EndpointConstants.CONSTANTS_PAGE_ORDER_READY;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SEARCH_ORDER_READY, e);

        }

    }

    @RequestMapping("/deleteCategories")
    public String deleteCategories(@RequestParam(value = EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID, required = false)
                                   int idCategories, Model theModel, Locale locale) {

        try {

            categoriesService.updateStatusCategories(idCategories);

            String message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_DELETE_CATEGORIES,
                    null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_CATEGORIES +
                    "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_DELETE_CATEGORIES, e);

        }

    }

    @RequestMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCT)
                             Product product, BindingResult theBindingResult,
                             @RequestParam(value = EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID, required = false)
                             int idCategories, Model theModel, Locale locale,
                             @AuthenticationPrincipal CustomUserDetails userDetails,
                             @RequestParam(value = EntityConstants.CONSTANTS_ENTITY_PRODUCT_IMAGES, required = false) String images) {

        try {

            product.setCategories(categoriesService.findingCategories(idCategories));
            product.setStatus(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ACTIVE);
            product.setUser(userDetails.getUser());

            List<String> imageUrls = Arrays.asList(images.split(",\\s*"));

            List<ImageProduct> imageProducts = new ArrayList<>();

            for (String url : imageUrls) {

                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setLink(url);
                imageProduct.setProduct(product);
                imageProducts.add(imageProduct);

            }

            product.setImages(imageProducts);
            product.getProductInfo().setDatePost(LocalDate.now());

            productService.saveProduct(product);

            String message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ADD_PRODUCT,
                    null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_PRODUCT_LIST +
                    "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8) + "&idCategories=" + idCategories;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SAVE_PRODUCT, e);

        }

    }

    @RequestMapping("/deleteProduct")
    public String deleteProduct(@RequestParam(EntityConstants.CONSTANTS_ENTITY_PRODUCTS_ID)
                                int idProduct, Model theModel, Locale locale) {

        try {

            Product product = productService.displayingProductInformation(idProduct);
            productService.updateStatusProduct(idProduct);

            String message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_DELETE_PRODUCT,
                    null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_PRODUCT_LIST +
                    "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8) + "&idCategories=" +
                    product.getCategories().getIdCategories();

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_DELETE_PRODUCT, e);

        }

    }

    @RequestMapping("/deleteReviews")
    public String deleteReviews(@RequestParam(EntityConstants.CONSTANTS_ENTITY_PRODUCTS_ID)
                                int idProduct, @RequestParam(EntityConstants.CONSTANTS_ENTITY_REVIEWS_ID)
                                int idReviews, Model theModel, Locale locale) {

        try {

            reviewsService.deleteReviews(idReviews);

            String message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_DELETE_REVIEWS,
                    null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_PRODUCT_INFO +
                    "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8) + "&idProduct=" + idProduct;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_DELETE_REVIEWS, e);

        }

    }

}
