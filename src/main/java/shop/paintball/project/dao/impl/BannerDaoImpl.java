package shop.paintball.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.BannerDao;
import shop.paintball.project.entity.Banner;

import java.util.List;

@Repository
public class BannerDaoImpl implements BannerDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public List<Banner> findAllBanner() {

        return getCurrentSession()
                .createQuery("FROM Banner WHERE status = :status", Banner.class)
                .setParameter("status", "active")
                .list();

    }

}
