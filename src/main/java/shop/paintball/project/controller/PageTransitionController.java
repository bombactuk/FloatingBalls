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
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.ControllerException;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.*;


@Controller
public class PageTransitionController {

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

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @RequestMapping("/showAuthorizationPage")
    public String authorizationPage(Model theModel) {

        return EndpointConstants.CONSTANTS_PAGE_AUTHORIZATION;

    }

    @RequestMapping("/showRegistrationPage")
    public String registrationPage(Model theModel) {

        theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_USER, new User());

        return EndpointConstants.CONSTANTS_PAGE_REGISTRATION;

    }

    @RequestMapping("/showAboutUsPage")
    public String aboutUsPage(Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_SHOP, shopService.findAllShop());

            return EndpointConstants.CONSTANTS_PAGE_ABOUT_US;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_SHOPS, e);

        }

    }

    @RequestMapping("/showMainPage")
    public String mainPage(Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_UPDATES, updateService.findAllUpdate());
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_BANNERS, bannerService.findAllBanner());

            return EndpointConstants.CONSTANTS_PAGE_MAIN;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_UPDATES_AND_BANNERS, e);

        }

    }

    @RequestMapping("/showCatalogPage")
    public String catalogPage(Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_CATEGORIES, categoriesService.getAllCategoriesWithProductCount());

            return EndpointConstants.CONSTANTS_PAGE_CATALOG;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_CATEGORIES, e);


        }

    }

    @RequestMapping("/showProductListPage")
    public String productListPage(@RequestParam(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID) int idCategories, Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCTS, productService.listOfProductsByCategory(idCategories));
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_CATEGORIES_ID, idCategories);

            return EndpointConstants.CONSTANTS_PAGE_PRODUCT_LIST;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_PRODUCTS, e);

        }

    }

    @RequestMapping("/showProductInfoPage")
    public String productInfoPage(@RequestParam(EntityConstants.CONSTANTS_ENTITY_PRODUCTS_ID) int idProduct, Model theModel) {

        try {

            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCT, productService.displayingProductInformation(idProduct));
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_PRODUCT_IMAGES, productService.getImagesByProductId(idProduct));
            theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_REVIEWS, reviewsService.displayAllProductReviews(idProduct));

            return EndpointConstants.CONSTANTS_PAGE_PRODUCT_INFO;

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_OUTPUT_INFO_PRODUCT, e);

        }

    }

    @RequestMapping("/showProfileUserPage")
    public String showProfileUser(Model theModel) {

        return EndpointConstants.CONSTANTS_PAGE_PROFILE;

    }

}
