package shop.paintball.project.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.UserDao;
import shop.paintball.project.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public boolean userRegistration(User userRegistration) {

        try {

            getCurrentSession().save(userRegistration);

            return true;

        } catch (Exception e) {

            // Обработка ошибки и логирование

            return false;

        }

    }

    @Override
    @Transactional
    public User checkingAnExistingUserByEmail(String login) {

        return getCurrentSession()
                .createQuery("from User where login = :login", User.class)
                .setParameter("login", login)
                .uniqueResult();

    }

}
