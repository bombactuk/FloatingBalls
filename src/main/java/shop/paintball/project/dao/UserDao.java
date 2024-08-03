package shop.paintball.project.dao;

import shop.paintball.project.entity.User;
import shop.paintball.project.exception.DaoException;

public interface UserDao {

    boolean userRegistration(User userRegistration) throws DaoException;

    User checkingAnExistingUserByEmail(String login) throws DaoException;

}
