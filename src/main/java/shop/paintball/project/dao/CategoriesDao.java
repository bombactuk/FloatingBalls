package shop.paintball.project.dao;

import shop.paintball.project.entity.Categories;
import shop.paintball.project.exception.DaoException;

import java.util.List;

public interface CategoriesDao {

    List<Categories> findAllCategories() throws DaoException;

    Categories getCategoryById(int id) throws DaoException;

}
