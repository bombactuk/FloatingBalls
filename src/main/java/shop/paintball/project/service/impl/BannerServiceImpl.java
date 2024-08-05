package shop.paintball.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.BannerDao;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.entity.Banner;
import shop.paintball.project.service.BannerService;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.constant.ErrorMessageConstantsService;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional
    public List<Banner> findAllBanner() throws ServiceException {

        try {

            return bannerDao.findAllBanner();

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_BANNERS, e);

        }

    }

}
