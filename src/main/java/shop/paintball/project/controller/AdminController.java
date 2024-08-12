package shop.paintball.project.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private OrderService orderService;

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
    public String sendingOrder(@ModelAttribute(EntityConstants.CONSTANTS_ENTITY_PROCESSING_ORDER) String trackingIndex,
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

}
