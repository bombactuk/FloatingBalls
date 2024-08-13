package shop.paintball.project.dao;

import shop.paintball.project.entity.Order;
import shop.paintball.project.entity.OrderShipping;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.DaoException;

import java.util.List;


public interface OrderDao {

    void saveOrder(Order order) throws DaoException;

    List<Order> findOrdersByUser(User user) throws DaoException;

    List<Order> outputOfOrdersForProcessing() throws DaoException;

    List<Order> outputOfOrdersForSending() throws DaoException;

    List<Order> outputOfOrdersForReady() throws DaoException;

    List<Order> searchOrderReadyList(String query) throws DaoException;

    Order findOrder(int idOrder) throws DaoException;

    void saveOrderShipping(OrderShipping orderShipping) throws DaoException;

    void updateOrderStatus(int idOrder, String status) throws DaoException;

    void saveOrderSending(int idOrder, String trackingIndex) throws DaoException;

}
