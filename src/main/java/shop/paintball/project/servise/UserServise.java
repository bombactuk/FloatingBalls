package shop.paintball.project.servise;

import shop.paintball.project.entity.User;

public interface UserServise {

    boolean userRegistration(User userRegistration);

    boolean checkingAnExistingUserByEmail(User user);

}
