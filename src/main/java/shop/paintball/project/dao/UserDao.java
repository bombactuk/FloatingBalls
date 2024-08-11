package shop.paintball.project.dao;

import shop.paintball.project.entity.Product;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.exception.ServiceException;

import java.util.Set;

public interface UserDao {

    boolean userRegistration(User userRegistration) throws DaoException;

    User checkingAnExistingUserByEmail(String login) throws DaoException;

}
