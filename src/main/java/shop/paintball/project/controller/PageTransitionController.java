package shop.paintball.project.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.paintball.project.controller.constant.EndpointConstants;
import shop.paintball.project.controller.constant.EntityConstants;
import shop.paintball.project.controller.constant.RequestConstants;
import shop.paintball.project.entity.User;


@Controller
public class PageTransitionController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(RequestConstants.CONSTANTS_REQUEST_SHOW_AUTHORIZATION_PAGE)
    public String authorizationPage(Model theModel) {

        return EndpointConstants.CONSTANTS_PAGE_AUTHORIZATION;

    }

    @RequestMapping(RequestConstants.CONSTANTS_REQUEST_SHOW_REGISTRATION_PAGE)
    public String registrationPage(Model theModel) {

        theModel.addAttribute(EntityConstants.CONSTANTS_ENTITY_USER, new User());

        return EndpointConstants.CONSTANTS_PAGE_REGISTRATION;

    }

    @RequestMapping(RequestConstants.CONSTANTS_REQUEST_SHOW_ABOUT_US_PAGE)
    public String aboutUsPage(Model theModel) {

        return EndpointConstants.CONSTANTS_PAGE_ABOUT_US;

    }

    @RequestMapping(RequestConstants.CONSTANTS_REQUEST_SHOW_MAIN_PAGE)
    public String mainPage(Model theModel) {

        return EndpointConstants.CONSTANTS_PAGE_MAIN;

    }

    @RequestMapping(RequestConstants.CONSTANTS_REQUEST_SHOW_CATALOG_PAGE)
    public String catalogPage(Model theModel) {

        return EndpointConstants.CONSTANTS_PAGE_CATALOG;

    }

}
