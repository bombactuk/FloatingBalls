package shop.paintball.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.ProductDao;
import shop.paintball.project.entity.ImageProduct;
import shop.paintball.project.entity.Product;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public List<Product> listOfProductsByCategory(int idCategory) {

        return getCurrentSession()
                .createQuery("FROM Product p LEFT JOIN FETCH p.images WHERE p.categories.idCategories = :idCategories",
                        Product.class)
                .setParameter("idCategories", idCategory)
                .list();

    }

    @Override
    @Transactional
    public Product displayingProductInformation(int idProduct) {

        return getCurrentSession()
                .createQuery("FROM Product WHERE idProduct = :idProduct", Product.class)
                .setParameter("idProduct", idProduct)
                .uniqueResult();

    }

    @Override
    @Transactional
    public List<Product> getProductsByCategoryId(int categoryId) {

        return getCurrentSession()
                .createQuery("from Product where categories.idCategories = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .list();

    }

    @Override
    @Transactional
    public List<ImageProduct> getImagesByProductId(int idProduct) {

        return getCurrentSession()
                .createQuery("from ImageProduct where product.idProduct = :idProduct", ImageProduct.class)
                .setParameter("idProduct", idProduct)
                .list();

    }

    @Override
    @Transactional
    public List<Product> searchProductsList(int idCategories, String query) {

        return sessionFactory.getCurrentSession().createQuery(
                        "FROM Product p LEFT JOIN FETCH p.images WHERE p.categories.idCategories = :idCategories AND (title LIKE :query)", Product.class)
                .setParameter("idCategories", idCategories)
                .setParameter("query", "%" + query + "%")
                .list();

    }

}
