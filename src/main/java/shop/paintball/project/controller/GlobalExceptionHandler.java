package shop.paintball.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import shop.paintball.project.controller.constant.EndpointConstants;
import shop.paintball.project.controller.constant.EntityConstants;
import shop.paintball.project.controller.constant.ErrorMessageConstantsController;
import shop.paintball.project.controller.constant.MessagePropertiesConstants;
import shop.paintball.project.exception.ControllerException;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.exception.ServiceException;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ControllerException.class)
    public ModelAndView handleControllerException(ControllerException ex, Locale locale) {

        ModelAndView modelAndView = new ModelAndView(EndpointConstants.CONSTANTS_PAGE_ERROR);
        modelAndView.addObject(EntityConstants.CONSTANTS_ENTITY_ERROR_MESSAGE,
                messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_PAGE, null, locale));

        return modelAndView;

    }

    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceException(ServiceException ex, Locale locale) {

        ModelAndView modelAndView = new ModelAndView(EndpointConstants.CONSTANTS_PAGE_ERROR);
        modelAndView.addObject(EntityConstants.CONSTANTS_ENTITY_ERROR_MESSAGE,
                messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_PAGE, null, locale));

        return modelAndView;

    }

    @ExceptionHandler(DaoException.class)
    public ModelAndView handleDaoException(DaoException ex, Locale locale) {

        ModelAndView modelAndView = new ModelAndView(EndpointConstants.CONSTANTS_PAGE_ERROR);
        modelAndView.addObject(EntityConstants.CONSTANTS_ENTITY_ERROR_MESSAGE,
                messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_PAGE, null, locale));

        return modelAndView;

    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Locale locale) {

        ModelAndView modelAndView = new ModelAndView(EndpointConstants.CONSTANTS_PAGE_ERROR);
        modelAndView.addObject(EntityConstants.CONSTANTS_ENTITY_ERROR_MESSAGE,
                messageSource.getMessage(MessagePropertiesConstants.CONSTANTS_MESSAGE_ERROR_PAGE, null, locale));

        return modelAndView;

    }

}
