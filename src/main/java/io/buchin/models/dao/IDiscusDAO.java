package io.buchin.models.dao;

import io.buchin.common.exceptions.DiscusDaoException;
import io.buchin.models.pojo.Discus;

import java.util.List;

/**
 * Created by yuri on 13.03.17.
 */
public interface IDiscusDAO {
    List<Discus> getDiscusByRiddleId(int idRiddle) throws DiscusDaoException;

    void addDiscus(Discus discus) throws DiscusDaoException;
}
