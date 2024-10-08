package shop.paintball.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.ProductDao;
import shop.paintball.project.entity.ImageProduct;
import shop.paintball.project.entity.Product;
import shop.paintball.project.service.ProductService;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.constant.ErrorMessageConstantsService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional
    public List<Product> listOfProductsByCategory(int idCategory) throws ServiceException {

        try {

            return productDao.listOfProductsByCategory(idCategory);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_PRODUCTS, e);

        }

    }

    @Override
    @Transactional
    public Product displayingProductInformation(int idProduct) throws ServiceException {

        try {

            return productDao.displayingProductInformation(idProduct);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_OUTPUT_INFO_PRODUCT, e);

        }

    }

    @Override
    @Transactional
    public List<ImageProduct> getImagesByProductId(int idProduct) throws ServiceException {

        try {

            return productDao.getImagesByProductId(idProduct);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_OUTPUT_IMAGE_PRODUCT, e);

        }

    }

    @Override
    @Transactional
    public List<Product> sortOptionsProductList(int idCategories, String sortBy) {

        try {

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

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_SORT_PRODUCT, e);

        }

    }

    @Override
    @Transactional
    public List<Product> searchProductsList(int idCategories, String query) throws ServiceException {

        try {

            return productDao.searchProductsList(idCategories, query);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_SEARCH_PRODUCT, e);

        }

    }

    @Override
    @Transactional
    public Set<Product> getFeaturedProducts(int idUser) throws ServiceException {

        try {

            return productDao.getFeaturedProducts(idUser);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_PRODUCT_FEATURED, e);

        }

    }

    @Override
    @Transactional
    public void addProductToFeatured(int idUser, int idProduct) throws ServiceException {

        try {

            productDao.addProductToFeatured(idUser, idProduct);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ADD_PRODUCT_FEATURED, e);

        }

    }

    @Override
    @Transactional
    public void removeProductFromFeatured(int idUser, int idProduct) throws ServiceException {

        try {

            productDao.removeProductFromFeatured(idUser, idProduct);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_REMOVE_PRODUCT_FEATURED, e);

        }

    }

    @Override
    @Transactional
    public void saveProduct(Product product) throws ServiceException {

        try {

            productDao.saveProduct(product);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_SAVE_PRODUCT_FEATURED, e);

        }

    }

    @Override
    @Transactional
    public void updateStatusProduct(int idProduct) throws ServiceException {

        try {

            productDao.updateStatusProduct(idProduct);

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_UPDATE_STATUS_PRODUCT, e);

        }

    }

}
