package shop.paintball.project.servise;

import shop.paintball.project.entity.User;

public interface UserService {

    boolean userRegistration(User userRegistration);

    User checkingAnExistingUserByEmail(String login);

}
