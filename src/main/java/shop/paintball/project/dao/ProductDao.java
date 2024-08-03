package shop.paintball.project.dao;

import shop.paintball.project.entity.ImageProduct;
import shop.paintball.project.entity.Product;
import shop.paintball.project.exception.DaoException;

import java.util.List;

public interface ProductDao {

    List<Product> listOfProductsByCategory(int idCategory) throws DaoException;

    Product displayingProductInformation(int idProduct) throws DaoException;

    List<ImageProduct> getImagesByProductId(int idProduct) throws DaoException;

    List<Product> searchProductsList(int idCategories, String query) throws DaoException;

}
