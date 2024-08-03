package shop.paintball.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.ShopDao;
import shop.paintball.project.entity.Shop;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.ShopService;
import shop.paintball.project.service.constant.ErrorMessageConstantsService;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public List<Shop> findAllShop() throws ServiceException {

        try {

            return shopDao.findAllShop();

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_SHOPS, e);

        }

    }

}
