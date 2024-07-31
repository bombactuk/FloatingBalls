package shop.paintball.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    @Transactional
    public List<Reviews> displayAllProductReviews(int idProduct) {

        return getCurrentSession()
                .createQuery("FROM Reviews WHERE product.idProduct = :idProduct", Reviews.class)
                .setParameter("idProduct", idProduct)
                .list();

    }

}
