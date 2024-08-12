package shop.paintball.project.service;

import shop.paintball.project.entity.Order;
import shop.paintball.project.entity.OrderShipping;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.ServiceException;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order) throws ServiceException;

    List<Order> findOrdersByUser(User user) throws ServiceException;

    List<Order> outputOfOrdersForProcessing() throws ServiceException;

    List<Order> outputOfOrdersForSending() throws ServiceException;

    Order findOrder(int idOrder) throws ServiceException;

    void saveOrderShipping(OrderShipping orderShipping) throws ServiceException;

    void updateOrderStatus(int idOrder, String status) throws ServiceException;

    void saveOrderSending(int idOrder, String trackingIndex) throws ServiceException;

}
