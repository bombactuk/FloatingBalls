package shop.paintball.project.servise;

import shop.paintball.project.entity.User;

public interface UserService {

    boolean userRegistration(User userRegistration);

    boolean checkingAnExistingUserByEmail(User user);

}
