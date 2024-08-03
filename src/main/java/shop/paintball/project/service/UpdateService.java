package shop.paintball.project.service;

import shop.paintball.project.entity.Update;
import shop.paintball.project.exception.ServiceException;

import java.util.List;

public interface UpdateService {

    List<Update> findAllUpdate() throws ServiceException;

}
