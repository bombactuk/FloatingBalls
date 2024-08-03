package shop.paintball.project.dao;

import shop.paintball.project.entity.Shop;
import shop.paintball.project.exception.DaoException;

import java.util.List;

public interface ShopDao {

    List<Shop> findAllShop() throws DaoException;

}
