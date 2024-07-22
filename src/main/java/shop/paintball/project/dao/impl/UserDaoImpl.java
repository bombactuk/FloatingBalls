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

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public boolean userRegistration(User userRegistration) {

        try {

            Session session = sessionFactory.getCurrentSession();

            session.save(userRegistration);

            return true;

        } catch (Exception e) {

            // Обработка ошибки и логирование

            return false;

        }

    }

    @Override
    @Transactional
    public User checkingAnExistingUserByEmail(User user) {

        return (User) sessionFactory.getCurrentSession()
                .createQuery("from User where login = :login")
                .setParameter("login", user.getLogin())
                .uniqueResult();

    }

}
