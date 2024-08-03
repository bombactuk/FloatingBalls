package shop.paintball.project.dao;

import shop.paintball.project.entity.Banner;
import shop.paintball.project.exception.DaoException;

import java.util.List;

public interface BannerDao {

    List<Banner> findAllBanner() throws DaoException;

}
