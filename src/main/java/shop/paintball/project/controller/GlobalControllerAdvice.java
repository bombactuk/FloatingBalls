package shop.paintball.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import shop.paintball.project.controller.constant.ErrorMessageConstantsController;
import shop.paintball.project.entity.SocialMedia;
import shop.paintball.project.exception.ControllerException;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.SocialMediaService;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private SocialMediaService socialMediaService;

    @ModelAttribute("socialMediaList")
    public List<SocialMedia> populateSocialMedia() {

        try {

            return socialMediaService.findAllSocialMedia();

        } catch (ServiceException e) {

            throw new ControllerException(ErrorMessageConstantsController.CONSTANTS_ERROR_MESSAGE_ALL_SOCIAL_MEDIA, e);

        }

    }

}
