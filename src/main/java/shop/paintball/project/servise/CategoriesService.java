package shop.paintball.project.servise;

import shop.paintball.project.entity.Categories;

import java.util.List;

public interface CategoriesService {

    List<Categories> findAllCategories();

    List<Categories> getAllCategoriesWithProductCount();

}
