package io.buchin.models.dao;

import io.buchin.common.exceptions.UserDaoException;
import io.buchin.models.pojo.User;

import java.util.List;

/**
 * Created by yuri on 11.03.17.
 */
public interface IUserDAO {
    User getUserByLoginAndPassword(String login, String password) throws UserDaoException;

    boolean registrationUser(String login, String password, String userName, String email) throws UserDaoException;

    List<User> getAllUsers() throws UserDaoException;

    void sendNotification(int id, int notification) throws UserDaoException;

    User getUserById(int id) throws UserDaoException;
}
