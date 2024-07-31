package shop.paintball.project.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.dao.SocialMediaDao;
import shop.paintball.project.entity.SocialMedia;
import shop.paintball.project.servise.SocialMediaService;

import java.util.List;

@Service
public class SocialMediaServiceImpl implements SocialMediaService {

    @Autowired
    private SocialMediaDao socialMediaDao;

    @Override
    public List<SocialMedia> findAllSocialMedia() {

        return socialMediaDao.findAllSocialMedia();

    }

}
