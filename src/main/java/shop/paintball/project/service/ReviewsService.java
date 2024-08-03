package shop.paintball.project.service;

import shop.paintball.project.entity.Reviews;
import shop.paintball.project.exception.ServiceException;

import java.util.List;

public interface ReviewsService {

    List<Reviews> displayAllProductReviews(int idProduct) throws ServiceException;

}
