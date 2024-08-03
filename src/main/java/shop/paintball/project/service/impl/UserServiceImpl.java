package shop.paintball.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.UserDao;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.UserService;
import shop.paintball.project.service.constant.ErrorMessageConstantsService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean userRegistration(User userRegistration) throws ServiceException {

        try {

            return userDao.userRegistration(userRegistration);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_SAVE_USER, e);

        }

    }

    @Override
    public User checkingAnExistingUserByEmail(String login) throws ServiceException {

        try {

            return userDao.checkingAnExistingUserByEmail(login);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_CHECKING_USER, e);

        }

    }

}
