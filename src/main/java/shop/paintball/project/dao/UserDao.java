package shop.paintball.project.dao;

import shop.paintball.project.entity.User;

public interface UserDao {

    boolean userRegistration(User userRegistration);

    User checkingAnExistingUserByEmail(User user);

}
