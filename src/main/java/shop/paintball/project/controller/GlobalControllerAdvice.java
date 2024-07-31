package shop.paintball.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import shop.paintball.project.entity.SocialMedia;
import shop.paintball.project.servise.SocialMediaService;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private SocialMediaService socialMediaService;

    @ModelAttribute("socialMediaList")
    public List<SocialMedia> populateSocialMedia() {

        return socialMediaService.findAllSocialMedia();

    }

}
