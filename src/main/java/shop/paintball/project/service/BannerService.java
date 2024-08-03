package shop.paintball.project.service;

import shop.paintball.project.entity.Banner;
import shop.paintball.project.exception.ServiceException;

import java.util.List;

public interface BannerService {

    List<Banner> findAllBanner() throws ServiceException;

}
