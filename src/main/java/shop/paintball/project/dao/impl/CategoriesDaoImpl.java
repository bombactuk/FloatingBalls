package shop.paintball.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.CategoriesDao;
import shop.paintball.project.entity.Categories;

import java.util.List;

@Repository
public class CategoriesDaoImpl implements CategoriesDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public List<Categories> findAllCategories() {

        return getCurrentSession()
                .createQuery("FROM Categories", Categories.class).list();

    }

    @Override
    @Transactional
    public Categories getCategoryById(int id) {

        return getCurrentSession().get(Categories.class,id);

    }

}
