package shop.paintball.project.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.dao.CategoriesDao;
import shop.paintball.project.dao.ProductDao;
import shop.paintball.project.entity.Categories;
import shop.paintball.project.entity.Product;
import shop.paintball.project.servise.CategoriesService;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesDao categoriesDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Categories> findAllCategories() {

        return categoriesDao.findAllCategories();

    }

    @Override
    public List<Categories> getAllCategoriesWithProductCount() {

        List<Categories> categories = categoriesDao.findAllCategories();

        for (Categories category : categories) {
            List<Product> products = productDao.getProductsByCategoryId(category.getIdCategories());
            category.setProductCount(products.size());
        }

        return categories;

    }

}
