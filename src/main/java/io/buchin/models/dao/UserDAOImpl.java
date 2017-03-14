package io.buchin.models.dao;

import io.buchin.common.exceptions.UserDaoException;
import io.buchin.models.connector.DBConst;
import io.buchin.models.pojo.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuri on 24.02.17.
 */
@Repository
public class UserDAOImpl implements IUserDAO {
    private static Logger logger = Logger.getLogger(UserDAOImpl.class);

    private static final String SQL_FIND_USER = "SELECT * FROM users where login=? AND password=?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users where id_user = ?";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users where login = ?";
    private static final String SQL_CREATE_USER = "INSERT INTO users (login, password, user_name, email) VALUES(?, ?, ?, ?)";
    private static final String SQL_ALL_USER = "SELECT * FROM users";
    private static final String SQL_UPDATE_RIDDLE = "UPDATE users SET notification = ? WHERE id_user = ?";

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws UserDaoException {
        User user = new User();
        logger.trace("Connection to DB in getUser method");

        Connection connPuts = null;
        PreparedStatement closePS = null;

        try {
            logger.trace("Get connection in getUser method");
            Connection conn = DBConst.connectionPool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_USER);

            connPuts = conn;
            closePS = preparedStatement;

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                logger.trace("Get ResultSet in getUser method");

                if (resultSet.getInt("admin_true") == 0) user.setAdminTrue(false);
                else user.setAdminTrue(true);

                if (resultSet.getInt("notification") == 0) user.setNotification(false);
                else user.setNotification(true);

                user.setIdUser(resultSet.getInt("id_user"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));


            }
        } catch (SQLException e) {
            logger.error("SqlExp in getUser method + errorMes - " + e);
            throw new UserDaoException();
        } finally {
            try {
                if (connPuts != null) closePS.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            logger.trace("Close PreparedStatement in getUser method");
            logger.trace("Return connection in getUser method");
            if (connPuts != null) DBConst.connectionPool.putback(connPuts);
        }

        logger.trace("Return users - id = " + user.getIdUser() + " login = " + user.getLogin());

        return user;
    }

    @Override
    public boolean registrationUser(String login, String password, String userName, String email) throws UserDaoException {
        logger.trace("Connection to DB in registration method");

        Connection connPuts = null;
        PreparedStatement closePS = null;

        try {
            logger.trace("Get connection in registrationUser method");

            Connection conn = DBConst.connectionPool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_USER);

            connPuts = conn;
            closePS = preparedStatement;

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, email);

            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                logger.debug("insert " + count);
                return true;
            } else {
                logger.debug(login + " " + password + " not inserted");
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        } finally {
            try {
                if (connPuts != null) closePS.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            logger.trace("Close PreparedStatement in registrationUser method");
            logger.trace("Return connection in registrationUser method");

            if (connPuts != null) DBConst.connectionPool.putback(connPuts);
        }

        return false;
    }

    @Override
    public List<User> getAllUsers() throws UserDaoException {
        List<User> usersList = new ArrayList<>();

        logger.trace("Connection to DB in getAllUsers method");

        Connection connPuts = null;
        Statement closeS = null;

        try {
            logger.trace("Get connection in getAllUsers method");

            Connection conn = DBConst.connectionPool.retrieve();
            Statement statement = conn.createStatement();

            connPuts = conn;
            closeS = statement;

            ResultSet resultSet = statement.executeQuery(SQL_ALL_USER);

            while (resultSet.next()) {
                User user = new User();

                if (resultSet.getInt("admin_true") == 0) user.setAdminTrue(false);
                else continue;

                if (resultSet.getInt("notification") == 0) user.setNotification(false);
                else user.setNotification(true);

                user.setIdUser(resultSet.getInt("id_user"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));

                usersList.add(user);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        } finally {
            try {
                if (connPuts != null) closeS.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            logger.trace("Close Statement in getAllUsers method");
            logger.trace("Return connection in getAllUsers method");
            if (connPuts != null) DBConst.connectionPool.putback(connPuts);
        }

        return usersList;
    }

    @Override
    public void sendNotification(int id, int notification) throws UserDaoException {
        logger.trace("Connection to DB in sendNotification method");

        Connection connPuts = null;
        PreparedStatement closePS = null;

        try {
            logger.trace("Get connection in sendNotification method");

            Connection conn = DBConst.connectionPool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_RIDDLE);

            connPuts = conn;
            closePS = preparedStatement;

            preparedStatement.setInt(1, notification);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
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


    @Override
    public User getUserById(int id) throws UserDaoException {
        logger.trace("Connection to DB in getUserById method");

        User user = new User();

        Connection connPuts = null;
        PreparedStatement closePS = null;

        try {
            logger.trace("Get connection in getUserById method");

            Connection conn = DBConst.connectionPool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_USER_BY_ID);

            connPuts = conn;
            closePS = preparedStatement;

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                logger.debug("Find user by id - " + id);

                if (resultSet.getInt("admin_true") == 0) user.setAdminTrue(false);
                else user.setAdminTrue(true);

                if (resultSet.getInt("notification") == 0) user.setNotification(false);
                else user.setNotification(true);

                user.setIdUser(resultSet.getInt("id_user"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));

            } else {
                logger.debug("User by id - " + id + ", not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
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

        return user;
    }

    @Override
    public boolean findLogin(String login) throws UserDaoException {
        logger.trace("Connection to DB in registration method");

        Connection connPuts = null;
        PreparedStatement closePS = null;

        try {
            logger.trace("Get connection in registrationUser method");

            Connection conn = DBConst.connectionPool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_USER_BY_LOGIN);

            connPuts = conn;
            closePS = preparedStatement;

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                logger.trace("found " + login);
                return true;

            } else {
                logger.debug(login + " not found");
                return false;
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        } finally {
            try {
                if (connPuts != null) closePS.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            logger.trace("Close PreparedStatement in registrationUser method");
            logger.trace("Return connection in registrationUser method");

            if (connPuts != null) DBConst.connectionPool.putback(connPuts);
        }
    }
}

