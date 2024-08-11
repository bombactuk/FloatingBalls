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
import shop.paintball.project.entity.Categories;
import shop.paintball.project.entity.CustomUserDetails;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.ControllerException;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.CategoriesService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CategoriesService categoriesService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @RequestMapping("/addCategories")
    public String addCategories(@Valid @ModelAttribute(EntityConstants.CONSTANTS_ENTITY_USER) Categories categories,
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

}
