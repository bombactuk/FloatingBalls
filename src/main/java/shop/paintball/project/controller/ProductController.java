package shop.paintball.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.paintball.project.controller.constant.EndpointConstants;
import shop.paintball.project.controller.constant.EntityConstants;
import shop.paintball.project.servise.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/sortProductList")
    public String sortProductList(@RequestParam(value = EntityConstants.CONSTANTS_ENTITY_CATEGORIES_SORT_BY, required = false) String sortBy,
                                  @RequestParam(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID) int idCategories, Model theModel) {

        theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCTS, productService.sortOptionsProductList(idCategories, sortBy));
        theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID, idCategories);

        return EndpointConstants.CONSTANTS_PAGE_PRODUCT_LIST;

    }

    @RequestMapping("/searchProductsList")
    public String searchProductsList(@RequestParam(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_QUERY) String query,
                                     @RequestParam(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID) int idCategories, Model theModel) {

        theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCTS, productService.searchProductsList(idCategories, query));
        theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID, idCategories);

        return EndpointConstants.CONSTANTS_PAGE_PRODUCT_LIST;

    }

}
