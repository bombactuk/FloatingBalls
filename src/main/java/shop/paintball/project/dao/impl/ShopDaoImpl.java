package shop.paintball.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.constant.ErrorMessageConstantsDao;
import shop.paintball.project.dao.constant.ParameterConstantsDao;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.ShopDao;
import shop.paintball.project.entity.Shop;

import java.util.List;

@Repository
public class ShopDaoImpl implements ShopDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private static final String HQL_OUTPUT_OF_ALL_SHOPS = "FROM Shop WHERE status = :status";

    @Override
    @Transactional
    public List<Shop> findAllShop() throws DaoException {

        try {

            return getCurrentSession()
                    .createQuery(HQL_OUTPUT_OF_ALL_SHOPS, Shop.class)
                    .setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_STATUS, ParameterConstantsDao.CONSTANTS_PARAMETER_ACTIVE)
                    .list();

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_ALL_SHOPS, e);

        }

    }

}
