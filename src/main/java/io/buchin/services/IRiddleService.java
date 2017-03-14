package io.buchin.services;

import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.models.dao.IRiddleDAO;
import io.buchin.models.pojo.Riddle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by yuri on 11.03.17.
 */
public interface IRiddleService {

    List<Riddle> getAllRiddles() throws RiddleDaoException;

    Riddle getRiddleById(int id) throws RiddleDaoException;

    void blockOrUnblockRiddle(int id, int block) throws RiddleDaoException;

    void addRiddle(int id, Riddle riddle) throws RiddleDaoException;

    void updateRiddle(Riddle riddle) throws RiddleDaoException;

    void updateSolveRiddle(int idRiddle, int idUser) throws RiddleDaoException;

    boolean getSolveRiddle(int idRiddle, int idUser) throws RiddleDaoException;
}
