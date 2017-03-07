package io.buchin.services;

import io.buchin.common.exceptions.UserDaoException;
import io.buchin.models.dao.UserDAO;
import io.buchin.models.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuri on 24.02.17.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User authorize(String login, String password) throws UserDaoException {
        return userDAO.getUserByLoginAndPassword(login, password);
    }

    public boolean registration(String login, String password, String userName, String email) throws UserDaoException {
        return userDAO.registrationUser(login, password, userName, email);
    }

    public List<User> getAllUsers() throws UserDaoException {
        return userDAO.getAllUsers();
    }

    public void sendNotification(int id, int notification) throws UserDaoException {
        userDAO.sendNotification(id, notification);
    }

    public User getUserById(int id) throws UserDaoException {
        return userDAO.getUserById(id);
    }
}
