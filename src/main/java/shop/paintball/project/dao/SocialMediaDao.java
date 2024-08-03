package shop.paintball.project.dao;

import shop.paintball.project.entity.SocialMedia;
import shop.paintball.project.exception.DaoException;

import java.util.List;

public interface SocialMediaDao {

    List<SocialMedia> findAllSocialMedia() throws DaoException;

}
