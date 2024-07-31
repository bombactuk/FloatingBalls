package shop.paintball.project.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.dao.ReviewsDao;
import shop.paintball.project.entity.Reviews;
import shop.paintball.project.servise.ReviewsService;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    @Autowired
    private ReviewsDao reviewsDao;

    @Override
    public List<Reviews> displayAllProductReviews(int idProduct) {

        return reviewsDao.displayAllProductReviews(idProduct);

    }

}
