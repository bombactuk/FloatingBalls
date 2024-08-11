package shop.paintball.project.service;

import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import shop.paintball.project.entity.Order;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.ServiceException;

import java.util.List;

public interface OrderService {

     void saveOrder(Order order) throws ServiceException;

     List<Order> findOrdersByUser(User user) throws ServiceException;

     List<Order> outputOfOrdersForProcessing() throws ServiceException;

}
