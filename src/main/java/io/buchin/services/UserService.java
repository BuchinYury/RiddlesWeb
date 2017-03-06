package io.buchin.services;

import io.buchin.common.exceptions.UserDaoException;
import io.buchin.models.dao.UserDAO;
import io.buchin.models.pojo.User;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by yuri on 24.02.17.
 */
public class UserService {
    static Logger logger = Logger.getLogger(UserService.class);

    public static User authorize(String login, String password) throws UserDaoException {
        return UserDAO.getUserByLoginAndPassword(login, password);
    }

    public static boolean registration(String login, String password, String userName, String email) throws UserDaoException {
        return UserDAO.registrationUser(login, password, userName, email);
    }

    public static List<User> getAllUsers() throws UserDaoException {
        return UserDAO.getAllUsers();
    }

    public static void sendNotification(int id, int notification) throws UserDaoException {
        UserDAO.sendNotification(id, notification);
    }

    public static User getUserById(int id) throws UserDaoException {
        return UserDAO.getUserById(id);
    }
}
