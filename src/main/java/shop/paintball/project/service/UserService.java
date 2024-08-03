package shop.paintball.project.service;

import shop.paintball.project.entity.User;
import shop.paintball.project.exception.ServiceException;

public interface UserService {

    boolean userRegistration(User userRegistration) throws ServiceException;

    User checkingAnExistingUserByEmail(String login) throws ServiceException;

}
