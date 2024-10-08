package shop.paintball.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.paintball.project.dao.constant.ErrorMessageConstantsDao;
import shop.paintball.project.dao.constant.ParameterConstantsDao;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.ReviewsDao;
import shop.paintball.project.entity.Reviews;

import java.util.List;

@Repository
public class ReviewsDaoImpl implements ReviewsDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private static final String HQL_OUTPUT_OF_ALL_REVIEWS_BY_ID_PRODUCT = "FROM Reviews WHERE product.idProduct = :idProduct";

    @Override
    public List<Reviews> displayAllProductReviews(int idProduct) throws DaoException {

        try {

            return getCurrentSession()
                    .createQuery(HQL_OUTPUT_OF_ALL_REVIEWS_BY_ID_PRODUCT, Reviews.class)
                    .setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_ID_PRODUCT, idProduct)
                    .list();

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_ALL_REVIEWS, e);

        }

    }

    @Override
    public void saveReviews(Reviews reviews) throws DaoException {

        try {

            getCurrentSession().save(reviews);

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_SAVE_REVIEWS, e);

        }

    }

    @Override
    public void deleteReviews(int idReviews) throws DaoException {

        try {

            Reviews reviews = getCurrentSession().get(Reviews.class, idReviews);

            getCurrentSession().delete(reviews);

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_DELETE_REVIEWS, e);

        }

    }

}
