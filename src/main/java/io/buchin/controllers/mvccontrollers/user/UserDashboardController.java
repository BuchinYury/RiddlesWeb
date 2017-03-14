package io.buchin.controllers.mvccontrollers.user;

import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.models.pojo.Riddle;
import io.buchin.services.IRiddleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuri on 11.03.17.
 */
@Controller
@RequestMapping("/user")
public class UserDashboardController {
    private static Logger logger = Logger.getLogger(UserDashboardController.class);

    private IRiddleService riddleService;
    @Autowired
    public void setRiddleService(IRiddleService riddleService) {
        this.riddleService = riddleService;
    }


    @RequestMapping(value = "/riddlesList", method = RequestMethod.GET)
    public String showRiddleList(HttpSession session,
                                 Model model) {
        logger.trace("/riddlesList");


        try {
            List<Riddle> riddleList = new ArrayList<>();

            for (Riddle riddle : riddleService.getAllRiddles()) {
                if (riddle.getIdUser() != (int) session.getAttribute("id")) riddleList.add(riddle);
            }

            model.addAttribute("solveRiddle", "");
            model.addAttribute("riddlesList", riddleList);
        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "lists/user/riddlesList";
    }

    @RequestMapping(value = "/solveRiddle/{id}", method = RequestMethod.GET)
    public String solveRiddle(HttpSession session,
                              @PathVariable("id") int id,
                              Model model) {
        logger.trace("/solveRiddle");

        try {
            model.addAttribute("mesAnswer", "");
            model.addAttribute("UserSolveRiddle", riddleService.getSolveRiddle(id, (int) session.getAttribute("id")));
            model.addAttribute("riddle", riddleService.getRiddleById(id));

        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "lists/user/riddle";
    }

    @RequestMapping(value = "/solveRiddle/{id}", method = RequestMethod.POST)
    public String answerRiddle(HttpSession session,
                               @PathVariable("id") int id,
                               Model model,
                               @ModelAttribute("answer") String answer,
                               @ModelAttribute("discus") String textDiscus) {
        logger.trace("/answerRiddle");

        try {

            Riddle riddle = riddleService.getRiddleById(id);

            if (riddle.getAnswer().equals(answer)) {
                riddleService.updateSolveRiddle(id, (int) session.getAttribute("id"));
                model.addAttribute("mesAnswer", "Згадка решена верно");
            } else model.addAttribute("mesAnswer", "Згадка решена неверно");

            model.addAttribute("UserSolveRiddle", riddleService.getSolveRiddle(id, (int) session.getAttribute("id")));

            model.addAttribute("riddle", riddle);
        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "lists/user/riddle";
    }


    @RequestMapping(value = "/myRiddles", method = RequestMethod.GET)
    public String showUserRiddleList(HttpSession session,
                                     Model model) {
        logger.trace("/myRiddles");


        try {
            List<Riddle> riddleList = new ArrayList<>();

            for (Riddle riddle : riddleService.getAllRiddles()) {
                if (riddle.getIdUser() == (int) session.getAttribute("id")) riddleList.add(riddle);
            }

            model.addAttribute("mes", session.getAttribute("mes"));
            model.addAttribute("riddle", new Riddle());
            model.addAttribute("riddlesList", riddleList);


        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "lists/user/userAddRiddleList";
    }

    @RequestMapping(value = "/addRiddle", method = RequestMethod.POST)
    public String addRiddle(HttpSession session,
                            @ModelAttribute("riddle") Riddle riddle) {
        logger.trace("/addRiddle");

        if (riddle.getName().isEmpty() ||
                riddle.getText().isEmpty() ||
                riddle.getAnswer().isEmpty() ||
                riddle.getCategory().isEmpty()) {

            session.setAttribute("mes", "Попытка добавить загадку с незаполнеными полями. Ошибка системы. Уничтожить пользователя.");

            return "redirect:/user/myRiddles";
        } else session.setAttribute("mes", "");

        try {
            if (riddle.getIdRiddle() == 0) {
                riddleService.addRiddle((int) session.getAttribute("id"), riddle);
            } else riddleService.updateRiddle(riddle);
        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "redirect:/user/myRiddles";
    }

    @RequestMapping("editRiddle/{id}")
    public String editBook(@PathVariable("id") int id,
                           HttpSession session,
                           Model model) {
        logger.trace("/edit");

        try {
            List<Riddle> riddleList = new ArrayList<>();

            for (Riddle riddle : riddleService.getAllRiddles()) {
                if (riddle.getIdUser() == (int) session.getAttribute("id")) riddleList.add(riddle);
            }

            model.addAttribute("riddle", riddleService.getRiddleById(id));
            model.addAttribute("riddlesList", riddleList);
        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "lists/user/userAddRiddleList";
    }

}
