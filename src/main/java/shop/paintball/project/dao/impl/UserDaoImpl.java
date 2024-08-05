package shop.paintball.project.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import shop.paintball.project.dao.constant.ErrorMessageConstantsDao;
import shop.paintball.project.dao.constant.ParameterConstantsDao;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.UserDao;
import shop.paintball.project.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean userRegistration(User userRegistration) throws DaoException {

        try {
            userRegistration.setPassword(passwordEncoder.encode(userRegistration.getPassword()));

            getCurrentSession().save(userRegistration);

            return true;

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_SAVE_USER, e);

        }

    }

    private static final String HQL_CHECKING_FOR_USER_EXISTENCE = "FROM User WHERE login = :login";

    @Override
    public User checkingAnExistingUserByEmail(String login) throws DaoException {

        try {

            return getCurrentSession()
                    .createQuery(HQL_CHECKING_FOR_USER_EXISTENCE, User.class)
                    .setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_LOGIN, login)
                    .uniqueResult();

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_CHECKING_USER, e);

        }

    }

}
