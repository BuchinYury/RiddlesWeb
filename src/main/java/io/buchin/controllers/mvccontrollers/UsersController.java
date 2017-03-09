package io.buchin.controllers.mvccontrollers;

import io.buchin.common.exceptions.UserDaoException;
import io.buchin.models.pojo.User;
import io.buchin.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by yuri on 07.03.17.
 */
@Controller
public class UsersController {
    private static Logger logger = Logger.getLogger(UsersController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/usersList", method = RequestMethod.GET)
    public String showUsersListPage(Model model) {
        logger.trace("/userList");

        try {
            List<User> userList = userService.getAllUsers();
            model.addAttribute("usersList", userList);
        } catch (UserDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "lists/usersList";
    }


}
