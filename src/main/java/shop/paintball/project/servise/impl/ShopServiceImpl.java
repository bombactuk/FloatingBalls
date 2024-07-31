package shop.paintball.project.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.dao.ShopDao;
import shop.paintball.project.entity.Shop;
import shop.paintball.project.servise.ShopService;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public List<Shop> findAllShop() {
        return shopDao.findAllShop();
    }
}
