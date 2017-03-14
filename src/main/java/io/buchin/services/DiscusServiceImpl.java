package io.buchin.services;

import io.buchin.common.exceptions.DiscusDaoException;
import io.buchin.common.exceptions.UserDaoException;
import io.buchin.models.dao.IDiscusDAO;
import io.buchin.models.dao.IUserDAO;
import io.buchin.models.pojo.Discus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuri on 13.03.17.
 */
@Service
public class DiscusServiceImpl implements IDiscusService {
    private static Logger logger = Logger.getLogger(DiscusServiceImpl.class);

    private IDiscusDAO discusDAO;

    @Override
    @Autowired
    public void setDiscusDAO(IDiscusDAO discusDAO) {
        this.discusDAO = discusDAO;
    }

    private IUserDAO userDAO;

    @Override
    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<Discus> getDiscusByRiddleId(int idRiddle) throws DiscusDaoException {

        List<Discus> discusList = new ArrayList<>();

        for (Discus discus : discusDAO.getDiscusByRiddleId(idRiddle)) {
            try {
                discus.setUserName(userDAO.getUserById(discus.getUserId()).getUserName());
            } catch (UserDaoException e) {
                logger.error(e);
            }

            discusList.add(discus);
        }

        return discusList;
    }

    @Override
    public void addDiscus(Discus discus) throws DiscusDaoException {
        discusDAO.addDiscus(discus);
    }
}
