package shop.paintball.project.dao;

import shop.paintball.project.entity.ImageProduct;
import shop.paintball.project.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> listOfProductsByCategory(int idCategory);

    Product displayingProductInformation(int idProduct);

    List<Product> getProductsByCategoryId(int categoryId);

    List<ImageProduct> getImagesByProductId(int idProduct);

    List<Product> searchProductsList(int idCategories, String query);

}
