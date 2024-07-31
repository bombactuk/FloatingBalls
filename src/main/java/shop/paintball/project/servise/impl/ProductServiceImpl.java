package shop.paintball.project.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.dao.ProductDao;
import shop.paintball.project.entity.ImageProduct;
import shop.paintball.project.entity.Product;
import shop.paintball.project.servise.ProductService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> listOfProductsByCategory(int idCategory) {

        return productDao.listOfProductsByCategory(idCategory);

    }

    @Override
    public Product displayingProductInformation(int idProduct) {

        return productDao.displayingProductInformation(idProduct);

    }

    @Override
    public List<ImageProduct> getImagesByProductId(int idProduct) {

        return productDao.getImagesByProductId(idProduct);

    }

    @Override
    public List<Product> sortOptionsProductList(int idCategories, String sortBy) {

        List<Product> products = productDao.listOfProductsByCategory(idCategories);

        Map<String, Comparator<Product>> sortOptions = Map.of(
                "title_asc", Comparator.comparing(Product::getTitle),
                "title_desc", Comparator.comparing(Product::getTitle).reversed(),
                "price_asc", Comparator.comparing(Product::getPrice),
                "price_desc", Comparator.comparing(Product::getPrice).reversed()
        );

        Comparator<Product> comparator = sortOptions.getOrDefault(sortBy, Comparator.comparing(Product::getTitle));

        products.sort(comparator);

        return products;

    }

    @Override
    public List<Product> searchProductsList(int idCategories, String query) {

        return productDao.searchProductsList(idCategories, query);

    }


}
