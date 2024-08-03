package shop.paintball.project.service;

import shop.paintball.project.entity.Shop;
import shop.paintball.project.exception.ServiceException;

import java.util.List;

public interface ShopService {

    List<Shop> findAllShop() throws ServiceException;


}
