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
import shop.paintball.project.controller.constant.EndpointConstants;
import shop.paintball.project.controller.constant.EntityConstants;
import shop.paintball.project.controller.constant.MessageConstants;
import shop.paintball.project.controller.constant.RequestConstants;
import shop.paintball.project.entity.Role;
import shop.paintball.project.entity.Token;
import shop.paintball.project.entity.User;
import shop.paintball.project.servise.UserService;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Controller
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    private UserService userServise;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(RequestConstants.CONSTANTS_REQUEST_REGISTER_USER)
    public String registerUser(@Valid @ModelAttribute(EntityConstants.CONSTANTS_ENTITY_USER) User user,
                               BindingResult theBindingResult, Model model, Locale locale) {

        if (theBindingResult.hasErrors()) {

            return EndpointConstants.CONSTANTS_PAGE_REGISTRATION;

        }

        if (userServise.checkingAnExistingUserByEmail(user)) {

            model.addAttribute(MessageConstants.CONSTANTS_MESSAGE_ERROR_NOT_MAIL,
                    messageSource.getMessage(MessageConstants.CONSTANTS_MESSAGE_PROPERTIES_ERROR_NOT_MAIL, null, locale));

            return EndpointConstants.CONSTANTS_PAGE_REGISTRATION;

        }

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        Token token = new Token();

        token.setNumber("");
        token.setUser(user);

        role.setIdRole(1);
        roles.add(role);

        user.setRoles(roles);
        user.setToken(token);

        if (userServise.userRegistration(user)) {

            model.addAttribute(MessageConstants.CONSTANTS_MESSAGE_SUCCESSFUL_REGISTRATION,
                    messageSource.getMessage(MessageConstants.CONSTANTS_MESSAGE_PROPERTIES_SUCCESSFUL_REGISTRATION,
                            null, locale));

            return EndpointConstants.CONSTANTS_PAGE_AUTHORIZATION;

        } else {

            model.addAttribute(MessageConstants.CONSTANTS_MESSAGE_ERROR_REGISTRATION,
                    messageSource.getMessage(MessageConstants.CONSTANTS_MESSAGE_PROPERTIES_REGISTRATION, null, locale));

            return EndpointConstants.CONSTANTS_PAGE_REGISTRATION;

        }

    }

}
