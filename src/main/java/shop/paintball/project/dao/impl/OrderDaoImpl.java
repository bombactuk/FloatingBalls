package shop.paintball.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.paintball.project.dao.OrderDao;
import shop.paintball.project.dao.constant.ErrorMessageConstantsDao;
import shop.paintball.project.dao.constant.ParameterConstantsDao;
import shop.paintball.project.entity.Order;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.DaoException;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void saveOrder(Order order) throws DaoException {

        try {

            getCurrentSession().save(order);

        }catch (Exception e){

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_SAVE_ORDER, e);

        }

    }


    private static final String HQL_OUTPUT_OF_ALL_ORDER = "SELECT DISTINCT o FROM Order o JOIN FETCH o.products p WHERE o.user.idUser = :idUser";

    @Override
    public List<Order> findOrdersByUser(User user) throws DaoException{

        try {

            return getCurrentSession().createQuery(HQL_OUTPUT_OF_ALL_ORDER, Order.class)
                    .setParameter("idUser", user.getIdUser())
                    .list();

        }catch (Exception e){

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_ALL_ORDER, e);

        }

    }

    private static final String HQL_OUTPUT_OF_ALL_ORDER_FOR_PROCESSING = "SELECT DISTINCT o FROM Order o JOIN FETCH o.products p WHERE o.status = :status";

    @Override
    public List<Order> outputOfOrdersForProcessing() throws DaoException {

        try {

            return getCurrentSession().createQuery(HQL_OUTPUT_OF_ALL_ORDER_FOR_PROCESSING, Order.class)
                    .setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_STATUS, ParameterConstantsDao.CONSTANTS_PARAMETER_TREATMENT)
                    .list();

        }catch (Exception e){

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_ALL_ORDER_FOR_PROCESSING, e);

        }

    }

}
