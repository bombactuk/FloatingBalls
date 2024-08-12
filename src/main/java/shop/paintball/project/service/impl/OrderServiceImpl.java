package shop.paintball.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.OrderDao;
import shop.paintball.project.entity.Order;
import shop.paintball.project.entity.OrderShipping;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.OrderService;
import shop.paintball.project.service.constant.ErrorMessageConstantsService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Override
    @Transactional
    public void saveOrder(Order order) throws ServiceException {

        try {

            orderDao.saveOrder(order);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_SAVE_ORDER, e);

        }

    }

    @Override
    @Transactional
    public List<Order> findOrdersByUser(User user) throws ServiceException {

        try {

            return orderDao.findOrdersByUser(user);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_ORDER, e);

        }

    }

    @Override
    @Transactional
    public List<Order> outputOfOrdersForProcessing() throws ServiceException {

        try {

            return orderDao.outputOfOrdersForProcessing();

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_ORDER_FOR_PROCESSING, e);

        }

    }

    @Override
    @Transactional
    public List<Order> outputOfOrdersForSending() throws ServiceException {

        try {

            return orderDao.outputOfOrdersForSending();

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_ORDER_FOR_SENDING, e);

        }

    }

    @Override
    @Transactional
    public Order findOrder(int idOrder) throws ServiceException {

        try {

            return orderDao.findOrder(idOrder);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_ORDER_FOR_ID, e);

        }

    }

    @Override
    @Transactional
    public void saveOrderShipping(OrderShipping orderShipping) throws ServiceException {

        try {

            orderDao.saveOrderShipping(orderShipping);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_SAVE_ORDER_SHIPPING, e);

        }

    }

    @Override
    @Transactional
    public void updateOrderStatus(int idOrder, String status) throws ServiceException {

        try {

            orderDao.updateOrderStatus(idOrder, status);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_UPDATE_ORDER_STATUS, e);

        }

    }

    @Override
    @Transactional
    public void saveOrderSending(int idOrder, String trackingIndex) throws ServiceException {

        try {

            orderDao.saveOrderSending(idOrder, trackingIndex);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_SAVE_ORDER_SENDING, e);

        }

    }

}
