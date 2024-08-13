package shop.paintball.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.dao.CategoriesDao;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.ProductDao;
import shop.paintball.project.entity.Categories;
import shop.paintball.project.entity.Product;
import shop.paintball.project.service.CategoriesService;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.constant.ErrorMessageConstantsService;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesDao categoriesDao;

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional
    public List<Categories> findAllCategories() throws ServiceException {

        try {

            return categoriesDao.findAllCategories();

        } catch (Exception e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_CATEGORIES, e);

        }

    }

    @Override
    @Transactional
    public List<Categories> getAllCategoriesWithProductCount() throws ServiceException {

        try {

            List<Categories> categories = categoriesDao.findAllCategories();

            for (Categories category : categories) {
                List<Product> products = productDao.listOfProductsByCategory(category.getIdCategories());
                category.setProductCount(products.size());
            }

            return categories;

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_CATEGORIES_NUMBER_OF_PRODUCTS, e);

        }

    }

    @Override
    @Transactional
    public Categories findingCategories(int idCategories) throws ServiceException {

        try {

            return categoriesDao.getCategoryById(idCategories);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_FIND_CATEGORIES_ID, e);

        }

    }

    @Override
    @Transactional
    public void saveCategories(Categories categories) throws ServiceException {

        try {

            categoriesDao.saveCategories(categories);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_SAVE_CATEGORIES, e);

        }

    }

    @Override
    @Transactional
    public void updateStatusCategories(int idCategories) throws ServiceException {

        try {

            categoriesDao.updateStatusCategories(idCategories);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_UPDATE_STATUS_CATEGORIES, e);

        }

    }

}
