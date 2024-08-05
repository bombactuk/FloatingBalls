package shop.paintball.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.paintball.project.controller.constant.EndpointConstants;
import shop.paintball.project.controller.constant.EntityConstants;
import shop.paintball.project.controller.constant.ErrorMessageConstantsController;
import shop.paintball.project.exception.ControllerException;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @RequestMapping("/sortProductList")
    public String sortProductList(@RequestParam(value = EntityConstants.CONSTANTS_ENTITY_CATEGORIES_SORT_BY, required = false) String sortBy,
                                  @RequestParam(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID) int idCategories, Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCTS, productService.sortOptionsProductList(idCategories, sortBy));
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID, idCategories);

            return EndpointConstants.CONSTANTS_PAGE_PRODUCT_LIST;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SORT_PRODUCT, e);

        }

    }

    @RequestMapping("/searchProductsList")
    public String searchProductsList(@RequestParam(value = EntityConstants.CONSTANTS_ENTITY_CATEGORIES_QUERY, required = false) String query,
                                     @RequestParam(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID) int idCategories, Model theModel) {

        try {

            if (query == null) {

                theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCTS, productService.listOfProductsByCategory(idCategories));

            } else {

                theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCTS, productService.searchProductsList(idCategories, query));

            }

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID, idCategories);

            return EndpointConstants.CONSTANTS_PAGE_PRODUCT_LIST;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_SEARCH_PRODUCT, e);

        }

    }

}
