package shop.paintball.project.servise;

import shop.paintball.project.entity.ImageProduct;
import shop.paintball.project.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> listOfProductsByCategory(int idCategory);

    Product displayingProductInformation(int idProduct);

    List<ImageProduct> getImagesByProductId(int idProduct);

    List<Product> sortOptionsProductList(int idCategories, String sortBy);

    List<Product> searchProductsList(int idCategories, String query);

}
