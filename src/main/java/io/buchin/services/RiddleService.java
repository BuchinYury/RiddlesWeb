package io.buchin.services;

import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.models.dao.RiddleDAO;
import io.buchin.models.pojo.Riddle;

import java.util.List;

/**
 * Created by yuri on 25.02.17.
 */
public class RiddleService {

    public static List<Riddle> getAllRiddles() throws RiddleDaoException {
        return RiddleDAO.getAllRiddles();
    }

    public static Riddle getRiddleById(int id) throws RiddleDaoException {
        return RiddleDAO.getRiddleById(id);
    }

    public static void blockOrUnblockRiddle(int id, int block) throws RiddleDaoException {
        RiddleDAO.blockOrUnblockRiddle(id, block);
    }
}
