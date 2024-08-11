package shop.paintball.project.dao;

import shop.paintball.project.entity.Reviews;
import shop.paintball.project.exception.DaoException;

import java.util.List;

public interface ReviewsDao {

    List<Reviews> displayAllProductReviews(int idProduct) throws DaoException;

    void saveReviews(Reviews reviews) throws DaoException;

}
