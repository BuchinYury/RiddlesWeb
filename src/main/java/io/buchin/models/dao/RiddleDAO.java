package io.buchin.models.dao;

import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.models.connector.DBConst;
import io.buchin.models.pojo.Riddle;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuri on 25.02.17.
 */

@Repository
public class RiddleDAO {
    private static Logger logger = Logger.getLogger(RiddleDAO.class);

    private static final String SQL_ALL_RIDDLE = "SELECT * FROM riddles";
    private static final String SQL_FIND_RIDDLE = "SELECT * FROM riddles WHERE id_riddle = ?";
    private static final String SQL_UPDATE_RIDDLE = "UPDATE riddles SET block_true = ? WHERE id_riddle = ?";

    public List<Riddle> getAllRiddles() throws RiddleDaoException {
        List<Riddle> riddlesList = new ArrayList<>();

        logger.trace("Connection to DB in getAllRiddles method");

        Connection connPuts = null;
        Statement closeS = null;

        try {
            logger.trace("Get connection in getAllRiddles method");

            Connection conn = DBConst.connectionPool.retrieve();
            Statement statement = conn.createStatement();

            connPuts = conn;
            closeS = statement;

            ResultSet resultSet = statement.executeQuery(SQL_ALL_RIDDLE);

            while (resultSet.next()) {
                Riddle riddle = new Riddle();

                riddle.setIdRiddle(resultSet.getInt("id_riddle"));
                riddle.setName(resultSet.getString("short_name"));
                riddle.setText(resultSet.getString("text_riddle"));
                riddle.setAnswer(resultSet.getString("answer"));
                riddle.setLevel(resultSet.getInt("level"));
                riddle.setCategory(resultSet.getString("category"));
                riddle.setIdUser(resultSet.getInt("id_user"));

                if (resultSet.getInt("block_true") == 0) riddle.setBlock(false);
                else riddle.setBlock(true);

                riddlesList.add(riddle);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new RiddleDaoException();
        } finally {
            try {
                if (connPuts != null) closeS.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            logger.trace("Close statement in getAllRiddles method");
            logger.trace("Return connection in getAllRiddles method");
            if (connPuts != null) DBConst.connectionPool.putback(connPuts);
        }

        return riddlesList;
    }

    public Riddle getRiddleById(int id) throws RiddleDaoException {
        logger.trace("Connection to DB in getRiddleById method");

        Riddle riddle = new Riddle();

        Connection connPuts = null;
        PreparedStatement closePS = null;

        try {
            logger.trace("Get connection in getRiddleById method");

            Connection conn = DBConst.connectionPool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_RIDDLE);

            connPuts = conn;
            closePS = preparedStatement;

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                logger.debug("Find riddle by id - " + id);

                riddle.setIdRiddle(resultSet.getInt("id_riddle"));
                riddle.setName(resultSet.getString("short_name"));
                riddle.setText(resultSet.getString("text_riddle"));
                riddle.setAnswer(resultSet.getString("answer"));
                riddle.setLevel(resultSet.getInt("level"));
                riddle.setCategory(resultSet.getString("category"));
                riddle.setIdUser(resultSet.getInt("id_user"));

                if (resultSet.getInt("block_true") == 0) riddle.setBlock(false);
                else riddle.setBlock(true);

            } else {
                logger.debug("Riddle by id - " + id + ", not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RiddleDaoException();
        } finally {
            try {
                if (connPuts != null) closePS.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            logger.trace("Close PreparedStatement in getRiddleById method");
            logger.trace("Return connection in getRiddleById method");
            if (connPuts != null) DBConst.connectionPool.putback(connPuts);
        }

        return riddle;
    }

    public void blockOrUnblockRiddle(int id, int block) throws RiddleDaoException {
        logger.trace("Connection to DB in blockOrUnblockRiddle method");

        Connection connPuts = null;
        PreparedStatement closePS = null;

        try {
            logger.trace("Get connection in blockOrUnblockRiddle method");

            Connection conn = DBConst.connectionPool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_RIDDLE);

            connPuts = conn;
            closePS = preparedStatement;

            preparedStatement.setInt(1, block);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            throw new RiddleDaoException();
        } finally {
            try {
                if (connPuts != null) closePS.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            logger.trace("Close PreparedStatement in blockOrUnblockRiddle method");
            logger.trace("Return connection in blockOrUnblockRiddle method");
            if (connPuts != null) DBConst.connectionPool.putback(connPuts);
        }
    }
}