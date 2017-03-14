package io.buchin.models.dao;

import io.buchin.common.exceptions.DiscusDaoException;
import io.buchin.models.connector.DBConst;
import io.buchin.models.pojo.Discus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuri on 13.03.17.
 */
@Repository
public class DiscusDAOImpl implements IDiscusDAO {
    private static Logger logger = Logger.getLogger(RiddleDAOImpl.class);

    private static final String SQL_DISCUS_TO_RIDDLE = "SELECT * FROM discussion WHERE riddle_id = ?";
    private static final String SQL_ADD_RIDDLE = "INSERT INTO discussion (user_id, riddle_id, text_discus) VALUES(?,?,?)";

    @Override
    public List<Discus> getDiscusByRiddleId(int idRiddle) throws DiscusDaoException {
        List<Discus> discusList = new ArrayList<>();

        logger.trace("Connection to DB in getDiscusByRiddleId method");

        Connection connPuts = null;
        PreparedStatement closePS = null;

        try {
            logger.trace("Get connection in getDiscusByRiddleId method");

            Connection conn = DBConst.connectionPool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DISCUS_TO_RIDDLE);

            connPuts = conn;
            closePS = preparedStatement;

            preparedStatement.setInt(1, idRiddle);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                logger.trace("Get ResultSet in getUser method");

                Discus discus = new Discus(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        "",
                        resultSet.getInt("riddle_id"),
                        resultSet.getString("text_discus"));

                discusList.add(discus);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DiscusDaoException();
        } finally {
            try {
                if (connPuts != null) closePS.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            logger.trace("Close PreparedStatement in getDiscusByRiddleId method");
            logger.trace("Return connection in getDiscusByRiddleId method");

            if (connPuts != null) DBConst.connectionPool.putback(connPuts);
        }

        return discusList;

    }

    @Override
    public void addDiscus(Discus discus) throws DiscusDaoException {
        logger.trace("Connection to DB in addDiscus method");

        Connection connPuts = null;
        PreparedStatement closePS = null;

        try {
            logger.trace("Get connection in addDiscus method");

            Connection conn = DBConst.connectionPool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_RIDDLE);

            connPuts = conn;
            closePS = preparedStatement;

            preparedStatement.setInt(1, discus.getUserId());
            preparedStatement.setInt(2, discus.getRiddleId());
            preparedStatement.setString(3, discus.getText());

            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                logger.debug("insert " + count);
            } else {
                logger.debug(discus.getText() + "not inserted");
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new DiscusDaoException();
        } finally {
            try {
                if (connPuts != null) closePS.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            logger.trace("Close PreparedStatement in addDiscus method");
            logger.trace("Return connection in addDiscus method");

            if (connPuts != null) DBConst.connectionPool.putback(connPuts);
        }

    }
}
