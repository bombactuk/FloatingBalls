package shop.paintball.project.service;

import shop.paintball.project.entity.ImageProduct;
import shop.paintball.project.entity.Product;
import shop.paintball.project.exception.ServiceException;

import java.util.List;

public interface ProductService {

    List<Product> listOfProductsByCategory(int idCategory) throws ServiceException;

    Product displayingProductInformation(int idProduct) throws ServiceException;

    List<ImageProduct> getImagesByProductId(int idProduct) throws ServiceException;

    List<Product> sortOptionsProductList(int idCategories, String sortBy) throws ServiceException;

    List<Product> searchProductsList(int idCategories, String query) throws ServiceException;

}
