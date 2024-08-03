package shop.paintball.project.service;

import shop.paintball.project.entity.SocialMedia;
import shop.paintball.project.exception.ServiceException;

import java.util.List;

public interface SocialMediaService {

    List<SocialMedia> findAllSocialMedia() throws ServiceException;

}
