package shop.paintball.project.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.paintball.project.dao.constant.ErrorMessageConstantsDao;
import shop.paintball.project.dao.constant.ParameterConstantsDao;
import shop.paintball.project.entity.User;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.ProductDao;
import shop.paintball.project.entity.ImageProduct;
import shop.paintball.project.entity.Product;

import java.util.List;
import java.util.Set;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private static final String HQL_OUTPUT_OF_ALL_PRODUCTS_AND_IMAGE = "FROM Product p LEFT JOIN FETCH p.images" +
            " WHERE p.categories.idCategories = :idCategories AND p.status = :status";

    @Override
    public List<Product> listOfProductsByCategory(int idCategory) throws DaoException {

        try {

            return getCurrentSession()
                    .createQuery(HQL_OUTPUT_OF_ALL_PRODUCTS_AND_IMAGE, Product.class)
                    .setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_ID_CATEGORIES, idCategory)
                    .setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_STATUS,
                            ParameterConstantsDao.CONSTANTS_PARAMETER_ACTIVE)
                    .list();

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_ALL_PRODUCTS, e);

        }

    }

    private static final String HQL_OUTPUT_OF_INFO_PRODUCT_BY_ID = "FROM Product p LEFT JOIN FETCH p.images " +
            "WHERE p.idProduct = :idProduct";

    @Override
    public Product displayingProductInformation(int idProduct) throws DaoException {

        try {

            return getCurrentSession()
                    .createQuery(HQL_OUTPUT_OF_INFO_PRODUCT_BY_ID, Product.class)
                    .setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_ID_PRODUCT, idProduct)
                    .uniqueResult();

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_OUTPUT_INFO_PRODUCT, e);

        }

    }

    private static final String HQL_OUTPUT_OF_IMAGE_PRODUCT_BY_ID = "FROM ImageProduct" +
            " WHERE product.idProduct = :idProduct";

    @Override
    public List<ImageProduct> getImagesByProductId(int idProduct) throws DaoException {

        try {

            return getCurrentSession()
                    .createQuery(HQL_OUTPUT_OF_IMAGE_PRODUCT_BY_ID, ImageProduct.class)
                    .setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_ID_PRODUCT, idProduct)
                    .list();

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_OUTPUT_IMAGE_PRODUCT, e);

        }

    }

    private static final String HQL_OUTPUT_SEARCH_PRODUCT = "FROM Product p LEFT JOIN FETCH p.images" +
            " WHERE p.categories.idCategories = :idCategories AND (title LIKE :query)";

    @Override
    public List<Product> searchProductsList(int idCategories, String query) throws DaoException {

        try {

            return sessionFactory.getCurrentSession().createQuery(
                            HQL_OUTPUT_SEARCH_PRODUCT, Product.class)
                    .setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_ID_CATEGORIES, idCategories)
                    .setParameter(ParameterConstantsDao.CONSTANTS_PARAMETER_QUERY, "%" + query + "%")
                    .list();

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_SEARCH_PRODUCT, e);

        }

    }

    @Override
    public Set<Product> getFeaturedProducts(int idUser) throws DaoException {

        try {

            User user = getCurrentSession().get(User.class, idUser);
            Hibernate.initialize(user.getFeaturedProducts());

            for (Product product : user.getFeaturedProducts()) {
                Hibernate.initialize(product.getImages());
            }

            return user.getFeaturedProducts();

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_ALL_PRODUCT_FEATURED, e);

        }

    }

    @Override
    public void addProductToFeatured(int idUser, int idProduct) throws DaoException {

        try {

            User user = getCurrentSession().get(User.class, idUser);
            Product product = getCurrentSession().get(Product.class, idProduct);

            user.getFeaturedProducts().add(product);

            getCurrentSession().update(user);

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_ADD_PRODUCT_FEATURED, e);

        }

    }

    @Override
    public void removeProductFromFeatured(int idUser, int idProduct) throws DaoException {

        try {

            User user = getCurrentSession().get(User.class, idUser);
            Product product = getCurrentSession().get(Product.class, idProduct);


            user.getFeaturedProducts().remove(product);

            getCurrentSession().update(user);

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_REMOVE_PRODUCT_FEATURED, e);

        }

    }

    @Override
    public void saveProduct(Product product) throws DaoException {

        try {

            getCurrentSession().save(product);

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_SAVE_PRODUCT_FEATURED, e);

        }

    }

    @Override
    public void updateStatusProduct(int idProduct) throws DaoException {

        try {

            Product product = getCurrentSession().get(Product.class, idProduct);

            product.setStatus(ParameterConstantsDao.CONSTANTS_PARAMETER_INACTIVE);

            getCurrentSession().update(product);

        } catch (Exception e) {

            throw new DaoException(ErrorMessageConstantsDao.CONSTANTS_ERROR_MESSAGE_UPDATE_STATUS_PRODUCT, e);

        }

    }

}
