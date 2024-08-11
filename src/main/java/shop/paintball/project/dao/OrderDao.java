package shop.paintball.project.dao;

import shop.paintball.project.entity.Order;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.DaoException;

import java.util.List;


public interface OrderDao {

    void saveOrder(Order order) throws DaoException;

    List<Order> findOrdersByUser(User user) throws DaoException;

    List<Order> outputOfOrdersForProcessing() throws DaoException;

}
