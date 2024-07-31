package shop.paintball.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.SocialMediaDao;
import shop.paintball.project.entity.SocialMedia;

import java.util.List;

@Repository
public class SocialMediaDaoImpl implements SocialMediaDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public List<SocialMedia> findAllSocialMedia() {

        return getCurrentSession()
                .createQuery("FROM SocialMedia WHERE status = :status", SocialMedia.class)
                .setParameter("status", "active")
                .list();

    }

}
