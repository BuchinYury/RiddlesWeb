package io.buchin.controllers.mvccontrollers.admin;

import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.common.exceptions.UserDaoException;
import io.buchin.models.pojo.Riddle;
import io.buchin.models.pojo.User;
import io.buchin.services.IRiddleService;
import io.buchin.services.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by yuri on 14.03.17.
 */
@Controller
public class AdminDashboardController {
    private static Logger logger = Logger.getLogger(AdminDashboardController.class);

    private IRiddleService riddleService;

    @Autowired
    public void setRiddleService(IRiddleService riddleService) {
        this.riddleService = riddleService;
    }

    private IUserService userService;

    @Autowired
    public void setRiddleService(IUserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/riddlesList", method = RequestMethod.GET)
    public String showUsersListPage(HttpSession session,
                                    Model model) {
        logger.trace("/riddlesList");

        try {
            model.addAttribute("mes", "");
            model.addAttribute("riddle", new Riddle());
            model.addAttribute("riddlesList", riddleService.getAllRiddles());
        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "lists/admin/riddlesList";
    }

    @RequestMapping(value = "/addRiddle", method = RequestMethod.POST)
    public String addRiddle(HttpSession session,
                            Model model,
                            @ModelAttribute("riddle") Riddle riddle) {
        logger.trace("/addRiddle");

        if (riddle.getName().isEmpty() ||
                riddle.getText().isEmpty() ||
                riddle.getAnswer().isEmpty() ||
                riddle.getCategory().isEmpty()) {

            try {
                model.addAttribute("mes", "Попытка добавить загадку с незаполнеными полями. Ошибка системы. Уничтожить пользователя.");
                model.addAttribute("riddle", new Riddle());
                model.addAttribute("riddlesList", riddleService.getAllRiddles());
            } catch (RiddleDaoException e) {
                logger.error(e);
                return "redirect:/error";
            }

            return "lists/admin/riddlesList";
        } else session.setAttribute("mes", "");

        try {
            if (riddle.getIdRiddle() == 0) {
                riddleService.addRiddle((int) session.getAttribute("id"), riddle);
            } else riddleService.updateRiddle(riddle);
        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "lists/admin/riddlesList";
    }

    @RequestMapping("/editRiddle/{id}")
    public String editBook(@PathVariable("id") int id,
                           Model model) {
        logger.trace("/edit");

        try {
            model.addAttribute("riddle", riddleService.getRiddleById(id));
            model.addAttribute("riddlesList", riddleService.getAllRiddles());
        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "lists/admin/riddlesList";
    }

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

        return "lists/admin/usersList";
    }
}
