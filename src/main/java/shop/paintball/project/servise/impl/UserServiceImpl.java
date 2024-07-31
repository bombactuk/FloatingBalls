package shop.paintball.project.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.dao.UserDao;
import shop.paintball.project.entity.User;
import shop.paintball.project.servise.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean userRegistration(User userRegistration) {

        return userDao.userRegistration(userRegistration);

    }

    @Override
    public User checkingAnExistingUserByEmail(String login) {

        return userDao.checkingAnExistingUserByEmail(login);

    }

}
