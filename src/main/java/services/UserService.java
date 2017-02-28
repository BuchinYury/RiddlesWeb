package services;

import common.exceptions.UserDaoException;
import models.dao.UserDAO;
import models.pojo.User;
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
}
