package shop.paintball.project.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.paintball.project.dao.UpdateDao;
import shop.paintball.project.entity.Update;
import shop.paintball.project.servise.UpdateService;

import java.util.List;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private UpdateDao updateDao;

    @Override
    public List<Update> findAllUpdate() {

        return updateDao.findAllUpdate();

    }

}
