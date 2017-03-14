package io.buchin.services;

import io.buchin.common.exceptions.DiscusDaoException;
import io.buchin.models.dao.IDiscusDAO;
import io.buchin.models.dao.IUserDAO;
import io.buchin.models.pojo.Discus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by yuri on 13.03.17.
 */
public interface IDiscusService {
    @Autowired
    void setDiscusDAO(IDiscusDAO discusDAO);

    @Autowired
    void setUserDAO(IUserDAO userDAO);

    List<Discus> getDiscusByRiddleId(int idRiddle) throws DiscusDaoException;

    void addDiscus(Discus discus) throws DiscusDaoException;
}
