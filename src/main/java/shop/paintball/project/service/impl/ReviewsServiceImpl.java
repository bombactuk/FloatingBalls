package shop.paintball.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.ReviewsDao;
import shop.paintball.project.entity.Reviews;
import shop.paintball.project.service.ReviewsService;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.constant.ErrorMessageConstantsService;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    @Autowired
    private ReviewsDao reviewsDao;

    @Override
    @Transactional
    public List<Reviews> displayAllProductReviews(int idProduct) throws ServiceException {

        try {

            return reviewsDao.displayAllProductReviews(idProduct);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_REVIEWS, e);

        }

    }

    @Override
    @Transactional
    public void saveReviews(Reviews reviews) throws ServiceException {

        try {

            reviewsDao.saveReviews(reviews);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_SAVE_REVIEWS, e);

        }

    }

}
