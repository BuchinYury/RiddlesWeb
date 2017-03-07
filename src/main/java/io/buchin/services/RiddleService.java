package io.buchin.services;

import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.models.dao.RiddleDAO;
import io.buchin.models.pojo.Riddle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuri on 25.02.17.
 */
@Service
public class RiddleService {

    @Autowired
    private RiddleDAO riddleDAO;

    public List<Riddle> getAllRiddles() throws RiddleDaoException {
        return riddleDAO.getAllRiddles();
    }

    public Riddle getRiddleById(int id) throws RiddleDaoException {
        return riddleDAO.getRiddleById(id);
    }

    public void blockOrUnblockRiddle(int id, int block) throws RiddleDaoException {
        riddleDAO.blockOrUnblockRiddle(id, block);
    }
}
