package io.buchin.services;

import io.buchin.common.exceptions.UserDaoException;
import io.buchin.models.dao.IUserDAO;
import io.buchin.models.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by yuri on 11.03.17.
 */
public interface IUserService {

    User authorize(String login, String password) throws UserDaoException;

    boolean registration(String login, String password, String userName, String email) throws UserDaoException;

    List<User> getAllUsers() throws UserDaoException;

    void sendNotification(int id, int notification) throws UserDaoException;

    User getUserById(int id) throws UserDaoException;
}
