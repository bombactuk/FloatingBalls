package shop.paintball.project.service;

import shop.paintball.project.entity.ImageProduct;
import shop.paintball.project.entity.Product;
import shop.paintball.project.exception.ServiceException;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> listOfProductsByCategory(int idCategory) throws ServiceException;

    Product displayingProductInformation(int idProduct) throws ServiceException;

    List<ImageProduct> getImagesByProductId(int idProduct) throws ServiceException;

    List<Product> sortOptionsProductList(int idCategories, String sortBy) throws ServiceException;

    List<Product> searchProductsList(int idCategories, String query) throws ServiceException;

    Set<Product> getFeaturedProducts(int idUser) throws ServiceException;

    void addProductToFeatured(int idUser, int idProduct) throws ServiceException;

    void removeProductFromFeatured(int idUser, int idProduct) throws ServiceException;

    void saveProduct(Product product) throws ServiceException;

    void updateStatusProduct(int idProduct) throws ServiceException;

}
