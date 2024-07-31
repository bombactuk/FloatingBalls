package shop.paintball.project.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.dao.BannerDao;
import shop.paintball.project.entity.Banner;
import shop.paintball.project.servise.BannerService;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public List<Banner> findAllBanner() {

        return bannerDao.findAllBanner();

    }

}
