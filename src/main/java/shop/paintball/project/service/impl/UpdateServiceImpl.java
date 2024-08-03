package shop.paintball.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.exception.DaoException;
import shop.paintball.project.dao.UpdateDao;
import shop.paintball.project.entity.Update;
import shop.paintball.project.exception.ServiceException;
import shop.paintball.project.service.UpdateService;
import shop.paintball.project.service.constant.ErrorMessageConstantsService;

import java.util.List;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private UpdateDao updateDao;

    @Override
    public List<Update> findAllUpdate() throws ServiceException {

        try {

            return updateDao.findAllUpdate();

        } catch (DaoException e) {

            throw new ServiceException(ErrorMessageConstantsService.CONSTANTS_ERROR_MESSAGE_ALL_UPDATES, e);

        }

    }

}
