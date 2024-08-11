package shop.paintball.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.paintball.project.dao.CategoriesDao;
import shop.paintball.project.dao.constant.ErrorMessageConstantsDao;
import shop.paintball.project.dao.constant.ParameterConstantsDao;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.entity.Categories;

import java.util.List;

@Repository
public class CategoriesDaoImpl implements CategoriesDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private static final String HQL_OUTPUT_OF_ALL_CATEGORIES = "FROM Categories WHERE status=:status";

    @Override
    public List<Categories> findAllCategories() throws DaoException {

        try {

            return getCurrentSession()
                    .createQuery(HQL_OUTPUT_OF_ALL_CATEGORIES, Categories.class).setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_STATUS,
                            ParameterConstantsDao.CONSTANTS_PARAMETER_ACTIVE)
                    .list();

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_ALL_CATEGORIES, e);

        }

    }

    @Override
    public Categories getCategoryById(int id) throws DaoException {

        try {

            return getCurrentSession().get(Categories.class, id);

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_ID_CATEGORIES, e);

        }

    }

    @Override
    public void saveCategories(Categories categories) throws DaoException {

        try {

            getCurrentSession().save(categories);

        }catch (Exception e){

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_SAVE_CATEGORIES, e);

        }

    }

}
