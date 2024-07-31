package shop.paintball.project.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.UpdateDao;
import shop.paintball.project.entity.Update;

import java.util.List;

@Repository
public class UpdateDaoImpl implements UpdateDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Update> findAllUpdate() {

        return sessionFactory.getCurrentSession()
                .createQuery("FROM Update WHERE status = :status")
                .setParameter("status", "active")
                .list();

    }

}
