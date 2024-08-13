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
import shop.paintball.project.service.ProductService;
import shop.paintball.project.service.ReviewsService;
import shop.paintball.project.service.UserService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @RequestMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute(EntityConstants.CONSTANTS_ENTITY_USER) User user,
                               BindingResult theBindingResult, Model model, Locale locale) {

        try {

            if (theBindingResult.hasErrors()) {

                return EndpointConstants.CONSTANTS_PAGE_REGISTRATION;

            }

            if (userService.checkingAnExistingUserByEmail(user.getLogin()) != null) {

                model.addAttribute(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_NOT_MAIL,
                        messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_PROPERTIES_ERROR_NOT_MAIL,
                                null, locale));

                return EndpointConstants.CONSTANTS_PAGE_REGISTRATION;

            }

            Set<Role> roles = new HashSet<>();
            Role role = new Role();

            role.setIdRole(1);
            roles.add(role);

            user.setRoles(roles);

            if (userService.userRegistration(user)) {

                model.addAttribute(MessagePropertiesConstants.CONSTANTS_MESSAGE_SUCCESSFUL_REGISTRATION,
                        messageSource.getMessage(
                                MessagePropertiesConstants.CONSTANTS_MESSAGE_PROPERTIES_SUCCESSFUL_REGISTRATION,
                                null, locale));

                return EndpointConstants.CONSTANTS_PAGE_AUTHORIZATION;

            } else {

                model.addAttribute(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_REGISTRATION,
                        messageSource.getMessage(
                                MessagePropertiesConstants.CONSTANTS_MESSAGE_PROPERTIES_REGISTRATION,
                                null, locale));

                return EndpointConstants.CONSTANTS_PAGE_REGISTRATION;

            }


        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SAVE_USER, e);

        }

    }

    @RequestMapping("/addProductBasket")
    public String addProductBasket(@RequestParam(EntityConstants.CONSTANTS_ENTITY_PRODUCTS_ID) int idProduct,
                                   @RequestParam(EntityConstants.CONSTANTS_ENTITY_PRODUCTS_ID) int idCategories,
                                   Model theModel, HttpSession session, Locale locale) {

        try {

            List<Product> basket = (List<Product>) session.getAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET);

            if (basket == null) {

                basket = new ArrayList<>();

            }

            Product product = productService.displayingProductInformation(idProduct);

            basket.add(product);

            session.setAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET, basket);

            String message = messageSource.getMessage(
                    MessagePropertiesConstants.CONSTANTS_MESSAGE_ADD_PRODUCT_BASKET_SUCCESSFUL, null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_PRODUCT_INFO + "?idProduct=" + idProduct
                    + "&message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ADD_PRODUCT_BASKET, e);

        }

    }

    @RequestMapping("/removeProductBasket")
    public String removeProductBasket(@RequestParam(EntityConstants.CONSTANTS_ENTITY_PRODUCTS_ID) int idProduct,
                                      Model theModel, HttpSession session, Locale locale) {

        try {

            List<Product> basket = (List<Product>) session.getAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET);
            String message = "";

            if (basket != null) {

                basket.removeIf(product -> product.getIdProduct() == idProduct);

                session.setAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET, basket);

                message = messageSource.getMessage(
                        MessagePropertiesConstants.CONSTANTS_MESSAGE_REMOVE_PRODUCT_BASKET_SUCCESSFUL, null, locale);

            }

            return EndpointConstants.CONSTANTS_REDIRECT_BASKET + "?message=" +
                    URLEncoder.encode(message, StandardCharsets.UTF_8);

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_REMOVE_PRODUCT_BASKET, e);

        }

    }

    @RequestMapping("/addReview")
    public String addReview(@AuthenticationPrincipal CustomUserDetails userDetails,
                            @RequestParam(EntityConstants.CONSTANTS_ENTITY_PRODUCTS_ID) int idProduct,
                            @Valid @ModelAttribute(EntityConstants.CONSTANTS_ENTITY_ADD_REVIEWS) Reviews reviews,
                            BindingResult theBindingResult, Model theModel, Locale locale) {

        try {

            String message;

            if (theBindingResult.hasErrors()) {

                message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_SAVE_REVIEWS,
                        null, locale);

                return EndpointConstants.CONSTANTS_REDIRECT_PRODUCT_INFO + "?idProduct=" + idProduct +
                        "&message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

            }

            reviews.setUser(userDetails.getUser());
            reviews.setDatePost(LocalDate.now());
            reviews.setProduct(productService.displayingProductInformation(idProduct));

            reviewsService.saveReviews(reviews);

            message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_SAVE_REVIEWS,
                    null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_PRODUCT_INFO + "?idProduct=" + idProduct +
                    "&message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SAVE_REVIEWS, e);

        }

    }

    @RequestMapping("/processPayment")
    public String processPayment(HttpSession session, Locale locale) {

        session.removeAttribute(EntityConstants.CONSTANTS_ENTITY_BASKET);

        String message = messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ORDER_SUCCESSFUL,
                null, locale);

        return EndpointConstants.CONSTANTS_REDIRECT_BASKET +
                "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

    }

}
