package shop.paintball.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.ShopDao;
import shop.paintball.project.entity.Shop;

import java.util.List;

@Repository
public class ShopDaoImpl implements ShopDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public List<Shop> findAllShop() {

        return getCurrentSession()
                .createQuery("FROM Shop WHERE status = :status", Shop.class)
                .setParameter("status", "active")
                .list();

    }

}
