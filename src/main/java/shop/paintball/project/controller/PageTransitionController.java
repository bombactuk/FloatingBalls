package shop.paintball.project.controller;

import jakarta.servlet.http.HttpSession;
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
import shop.paintball.project.service.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class PageTransitionController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @RequestMapping("/showAuthorizationPage")
    public String showAuthorizationPage(Model theModel) {

        return EndpointConstants.CONSTANTS_PAGE_AUTHORIZATION;

    }

    @RequestMapping("/showRegistrationPage")
    public String showRegistrationPage(Model theModel) {

        theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_USER, new User());

        return EndpointConstants.CONSTANTS_PAGE_REGISTRATION;

    }

    @RequestMapping("/showAboutUsPage")
    public String showAboutUsPage(Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_SHOP, shopService.findAllShop());

            return EndpointConstants.CONSTANTS_PAGE_ABOUT_US;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_SHOPS, e);

        }

    }

    @RequestMapping("/showMainPage")
    public String showMainPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_UPDATES, updateService.findAllUpdate());
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_BANNERS, bannerService.findAllBanner());

            return EndpointConstants.CONSTANTS_PAGE_MAIN;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_UPDATES_AND_BANNERS, e);

        }

    }

    @RequestMapping("/showCatalogPage")
    public String showCatalogPage(@RequestParam(value = EntityConstants.CONSTANTS_ENTITY_MESSAGE, required = false)
                                  String message,
                                  Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_CATEGORIES,
                    categoriesService.getAllCategoriesWithProductCount());
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_ADD_CATEGORIES, new Categories());
            theModel.addAttribute(MessagePropertiesConstants.CONSTANTS_MESSAGE_SUCCESSFUL, message);

            return EndpointConstants.CONSTANTS_PAGE_CATALOG;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_CATEGORIES, e);


        }

    }

    @RequestMapping("/showProductListPage")
    public String showProductListPage(@RequestParam(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID) int idCategories,
                                      @RequestParam(value = EntityConstants.CONSTANTS_ENTITY_MESSAGE, required = false)
                                      String message, Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCTS,
                    productService.listOfProductsByCategory(idCategories));
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID, idCategories);
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCT, new Product());
            theModel.addAttribute(MessagePropertiesConstants.CONSTANTS_MESSAGE_SUCCESSFUL, message);

            return EndpointConstants.CONSTANTS_PAGE_PRODUCT_LIST;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_PRODUCTS, e);

        }

    }

    @RequestMapping("/showProductInfoPage")
    public String showProductInfoPage(@AuthenticationPrincipal CustomUserDetails userDetails,
                                      @RequestParam(EntityConstants.CONSTANTS_ENTITY_PRODUCTS_ID) int idProduct,
                                      @RequestParam(value = EntityConstants.CONSTANTS_ENTITY_MESSAGE, required = false)
                                      String message,
                                      Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCT,
                    productService.displayingProductInformation(idProduct));
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCT_IMAGES,
                    productService.getImagesByProductId(idProduct));
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_REVIEWS,
                    reviewsService.displayAllProductReviews(idProduct));
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_ADD_REVIEWS, new Reviews());
            theModel.addAttribute(MessagePropertiesConstants.CONSTANTS_MESSAGE_SUCCESSFUL, message);

            return EndpointConstants.CONSTANTS_PAGE_PRODUCT_INFO;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_OUTPUT_INFO_PRODUCT, e);

        }

    }

    @RequestMapping("/showProfileUserPage")
    public String showProfileUser(@AuthenticationPrincipal CustomUserDetails userDetails, Model theModel) {

        theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_USER, userDetails.getUser());

        return EndpointConstants.CONSTANTS_PAGE_PROFILE;

    }

    @RequestMapping("/showProductFeaturedPage")
    public String showFeaturedProducts(@AuthenticationPrincipal CustomUserDetails userDetails,
                                       @RequestParam(value = EntityConstants.CONSTANTS_ENTITY_MESSAGE, required = false)
                                       String message,
                                       Model theModel) {

        try {

            User user = userDetails.getUser();
            Set<Product> featuredProducts = productService.getFeaturedProducts(user.getIdUser());

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCT_FEATURED, featuredProducts);
            theModel.addAttribute(MessagePropertiesConstants.CONSTANTS_MESSAGE_SUCCESSFUL, message);

            return EndpointConstants.CONSTANTS_PAGE_FEATURED_PRODUCTS;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_PRODUCT_FEATURED, e);

        }

    }

    @RequestMapping("/showBasketPage")
    public String showBasket(Model theModel, HttpSession session,
                             @RequestParam(value = EntityConstants.CONSTANTS_ENTITY_MESSAGE, required = false)
                             String message) {

        List<Product> basket = (List<Product>) session.getAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET);

        if (basket == null) {

            basket = new ArrayList<>();

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET_SUM, 0);

        } else {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET_SUM, basket.stream()
                    .mapToInt(Product::getPrice)
                    .sum());

        }

        theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET, basket);
        theModel.addAttribute(MessagePropertiesConstants.CONSTANTS_MESSAGE_SUCCESSFUL, message);
        theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_ORDER, new Order());

        return EndpointConstants.CONSTANTS_PAGE_BASKET;

    }

    @RequestMapping("/checkoutPage")
    public String showCheckout(@ModelAttribute(EntityConstants.CONSTANTS_ENTITY_ORDER) @Valid Order order,
                               BindingResult theBindingResult,
                               @AuthenticationPrincipal CustomUserDetails userDetails,
                               Model theModel, HttpSession session, Locale locale) {

        try {

            if (theBindingResult.hasErrors()) {

                String message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_PRODUCT_BASKET,
                        null, locale);

                return EndpointConstants.CONSTANTS_REDIRECT_BASKET + "?message=" + URLEncoder.encode(message,
                        StandardCharsets.UTF_8);

            }

            List<Product> basket = (List<Product>) session.getAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET);

            order.setUser(userDetails.getUser());
            order.setStatus(EntityConstants.CONSTANTS_ENTITY_ORDER_STATUS);
            order.setProducts(basket);
            order.setDatePost(LocalDate.now());

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET_SUM, basket.stream()
                    .mapToInt(Product::getPrice)
                    .sum());

            orderService.saveOrder(order);

            return EndpointConstants.CONSTANTS_PAGE_CHECKOUT;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SAVE_ORDER, e);

        }

    }

    @RequestMapping("/showPurchaseHistory")
    public String showPurchaseHistory(@AuthenticationPrincipal CustomUserDetails userDetails, Model theModel) {

        try {

            List<Order> orders = orderService.findOrdersByUser(userDetails.getUser());

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_ORDERS, orders.stream()
                    .sorted(Comparator.comparing(Order::getDatePost))
                    .collect(Collectors.toList()));

            return EndpointConstants.CONSTANTS_PAGE_PURCHASE_HISTORY;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_ORDER, e);

        }

    }

}
