package io.buchin.controllers.mvccontrollers.admin;

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

/**
 * Created by yuri on 07.03.17.
 */
@Controller
public class RiddlesController {
    private static Logger logger = Logger.getLogger(RiddlesController.class);

    private IRiddleService riddleService;

    @Autowired
    public void setRiddleService(IRiddleService riddleService) {
        this.riddleService = riddleService;
    }

    @RequestMapping(value = "/riddlesList", method = RequestMethod.GET)
    public String showUsersListPage(HttpSession session,
                                    Model model) {
        logger.trace("/riddlesList");

        try {
            model.addAttribute("mes", session.getAttribute("mes"));
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
                            @ModelAttribute("riddle") Riddle riddle) {
        logger.trace("/addRiddle");

        if (riddle.getName().isEmpty() ||
                riddle.getText().isEmpty() ||
                riddle.getAnswer().isEmpty() ||
                riddle.getCategory().isEmpty()) {

            session.setAttribute("mes", "Попытка добавить загадку с незаполнеными полями. Ошибка системы. Уничтожить пользователя.");

            return "redirect:/riddlesList";
        } else  session.setAttribute("mes", "");

        try {
            if (riddle.getIdRiddle() == 0) {
                riddleService.addRiddle((int) session.getAttribute("id"), riddle);
            } else riddleService.updateRiddle(riddle);
        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "redirect:/riddlesList";
    }

    @RequestMapping("editRiddle/{id}")
    public String editBook(@PathVariable("id") int id,
                           Model model){
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
}
