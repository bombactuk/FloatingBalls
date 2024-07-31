package shop.paintball.project.dao;

import shop.paintball.project.entity.Categories;

import java.util.List;

public interface CategoriesDao {

    List<Categories> findAllCategories();

    Categories getCategoryById(int id);

}
