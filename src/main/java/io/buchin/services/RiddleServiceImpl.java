package io.buchin.services;

import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.models.dao.IRiddleDAO;
import io.buchin.models.pojo.Riddle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuri on 25.02.17.
 */
@Service
public class RiddleServiceImpl implements IRiddleService {

    private IRiddleDAO riddleDAO;

    @Autowired
    public void setRidleDAO(IRiddleDAO riddleDAO) {
        this.riddleDAO = riddleDAO;
    }

    @Override
    public List<Riddle> getAllRiddles() throws RiddleDaoException {
        return riddleDAO.getAllRiddles();
    }

    @Override
    public Riddle getRiddleById(int id) throws RiddleDaoException {
        return riddleDAO.getRiddleById(id);
    }

    @Override
    public void blockOrUnblockRiddle(int id, int block) throws RiddleDaoException {
        riddleDAO.blockOrUnblockRiddle(id, block);
    }

    @Override
    public void addRiddle(int id, Riddle riddle) throws RiddleDaoException {
        riddleDAO.addRiddle(id, riddle);
    }

    @Override
    public void updateRiddle(Riddle riddle) throws RiddleDaoException {
        riddleDAO.updateRiddle(riddle);
    }

    @Override
    public void updateSolveRiddle(int idRiddle, int idUser) throws RiddleDaoException {
        riddleDAO.updateSolveRiddle(idRiddle, idUser);
    }

    @Override
    public boolean getSolveRiddle(int idRiddle, int idUser) throws RiddleDaoException {
        return riddleDAO.getSolveRiddle(idRiddle, idUser);
    }


}
