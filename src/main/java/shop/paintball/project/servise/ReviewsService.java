package shop.paintball.project.servise;

import shop.paintball.project.entity.Reviews;

import java.util.List;

public interface ReviewsService {

    List<Reviews> displayAllProductReviews(int idProduct);

}
