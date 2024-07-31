package shop.paintball.project.dao;

import shop.paintball.project.entity.Reviews;

import java.util.List;

public interface ReviewsDao {

    List<Reviews> displayAllProductReviews(int idProduct);

}
