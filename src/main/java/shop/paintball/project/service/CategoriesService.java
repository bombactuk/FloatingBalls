package shop.paintball.project.service;

import shop.paintball.project.entity.Categories;
import shop.paintball.project.exception.ServiceException;

import java.util.List;

public interface CategoriesService {

    List<Categories> findAllCategories() throws ServiceException;

    List<Categories> getAllCategoriesWithProductCount() throws ServiceException;

    Categories findingCategories(int idCategories) throws ServiceException;

    void saveCategories(Categories categories) throws ServiceException;

    void updateStatusCategories(int idCategories) throws ServiceException;

}
