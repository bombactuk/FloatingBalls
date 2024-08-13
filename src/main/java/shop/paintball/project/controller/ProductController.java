package shop.paintball.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.paintball.project.controller.constant.EndpointConstants;
import shop.paintball.project.controller.constant.EntityConstants;
import shop.paintball.project.controller.constant.ErrorMessageConstantsController;
import shop.paintball.project.controller.constant.MessagePropertiesConstants;
import shop.paintball.project.entity.CustomUserDetails;
import shop.paintball.project.exception.ControllerException;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.ProductService;
import shop.paintball.project.service.ReviewsService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class ProductController {

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

    @RequestMapping("/sortProductList")
    public String sortProductList(@RequestParam(value = EntityConstants.CONSTANTS_ENTITY_CATEGORIES_SORT_BY, required = false)
                                  String sortBy,
                                  @RequestParam(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID) int idCategories,
                                  Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCTS,
                    productService.sortOptionsProductList(idCategories, sortBy));
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID, idCategories);

            return EndpointConstants.CONSTANTS_PAGE_PRODUCT_LIST;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SORT_PRODUCT, e);

        }

    }

    @RequestMapping("/searchProductsList")
    public String searchProductsList(@RequestParam(value = EntityConstants.CONSTANTS_ENTITY_CATEGORIES_QUERY, required = false)
                                     String query,
                                     @RequestParam(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID) int idCategories,
                                     Model theModel) {

        try {

            if (query == null) {

                theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCTS,
                        productService.listOfProductsByCategory(idCategories));

            } else {

                theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCTS,
                        productService.searchProductsList(idCategories, query));

            }

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID, idCategories);

            return EndpointConstants.CONSTANTS_PAGE_PRODUCT_LIST;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SEARCH_PRODUCT, e);

        }

    }

    @RequestMapping("/addProductFeatured")
    public String addProductFeatured(@AuthenticationPrincipal CustomUserDetails userDetails,
                                     @RequestParam(EntityConstants.CONSTANTS_ENTITY_PRODUCTS_ID) int idProduct,
                                     Model theModel, Locale locale) {

        try {

            productService.addProductToFeatured(userDetails.getUser().getIdUser(), idProduct);

            String message = messageSource.getMessage(
                    MessagePropertiesConstants.CONSTANTS_MESSAGE_ADD_PRODUCT_FEATURED_SUCCESSFUL, null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_PRODUCT_INFO + "?idProduct=" + idProduct +
                    "&message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ADD_PRODUCT_FEATURED, e);

        }

    }

    @RequestMapping("/removeProductFeatured")
    public String removeProductFeatured(@AuthenticationPrincipal CustomUserDetails userDetails,
                                        @RequestParam(EntityConstants.CONSTANTS_ENTITY_PRODUCTS_ID) int idProduct,
                                        Model theModel, Locale locale) {

        try {

            productService.removeProductFromFeatured(userDetails.getUser().getIdUser(), idProduct);

            String message = messageSource.getMessage(
                    MessagePropertiesConstants.CONSTANTS_MESSAGE_REMOVE_PRODUCT_FEATURED_SUCCESSFUL, null, locale);

            return EndpointConstants.CONSTANTS_REDIRECT_PRODUCT_FEATURED +
                    "?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_REMOVE_PRODUCT_FEATURED, e);

        }

    }

}
