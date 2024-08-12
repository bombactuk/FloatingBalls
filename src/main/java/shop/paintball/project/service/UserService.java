package shop.paintball.project.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.ServiceException;


public interface UserService extends UserDetailsService {

    boolean userRegistration(User userRegistration) throws ServiceException;

    User checkingAnExistingUserByEmail(String login) throws ServiceException;

}
