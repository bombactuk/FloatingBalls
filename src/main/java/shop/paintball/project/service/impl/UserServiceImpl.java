package shop.paintball.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.entity.CustomUserDetails;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.UserDao;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.UserService;
import shop.paintball.project.service.constant.ErrorMessageConstantsService;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean userRegistration(User userRegistration) throws ServiceException {

        try {

            return userDao.userRegistration(userRegistration);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_SAVE_USER, e);

        }

    }

    @Override
    @Transactional
    public User checkingAnExistingUserByEmail(String login) throws ServiceException {

        try {

            return userDao.checkingAnExistingUserByEmail(login);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_CHECKING_USER, e);

        }

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.checkingAnExistingUserByEmail(username);

        if (user == null) {

            throw new UsernameNotFoundException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_USER_NOT_FOUND);

        }

        user.getRoles().size();

        return new CustomUserDetails(user);

    }

}
