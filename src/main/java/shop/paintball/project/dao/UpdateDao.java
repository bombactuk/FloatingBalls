package shop.paintball.project.dao;

import shop.paintball.project.entity.Update;
import shop.paintball.project.exception.DaoException;

import java.util.List;

public interface UpdateDao {

    List<Update> findAllUpdate() throws DaoException;

}
