package shop.paintball.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.SocialMediaDao;
import shop.paintball.project.entity.SocialMedia;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.SocialMediaService;
import shop.paintball.project.service.constant.ErrorMessageConstantsService;

import java.util.List;

@Service
public class SocialMediaServiceImpl implements SocialMediaService {

    @Autowired
    private SocialMediaDao socialMediaDao;

    @Override
    @Transactional
    public List<SocialMedia> findAllSocialMedia() throws ServiceException {

        try {

            return socialMediaDao.findAllSocialMedia();

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_SOCIAL_MEDIA, e);

        }

    }

}
