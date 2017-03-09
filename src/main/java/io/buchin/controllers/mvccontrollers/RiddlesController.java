package io.buchin.controllers.mvccontrollers;

import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.models.pojo.Riddle;
import io.buchin.services.RiddleService;
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
 * Created by yuri on 07.03.17.
 */
@Controller
public class RiddlesController {
    private static Logger logger = Logger.getLogger(RiddlesController.class);

    @Autowired
    private RiddleService riddleService;

    @RequestMapping(value = "/riddlesList", method = RequestMethod.GET)
    public String showUsersListPage(Model model) {
        logger.trace("/riddlesList");

        try {
            List<Riddle> riddlesList = riddleService.getAllRiddles();
            model.addAttribute("riddle", new Riddle());
            model.addAttribute("riddlesList", riddlesList);
        } catch (RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "lists/riddlesList";
    }

    @RequestMapping(value = "/addRiddle", method = RequestMethod.POST)
    public String addRiddle(HttpSession session,
                            @ModelAttribute("riddle") Riddle riddle) {
        logger.trace("/addRiddle");

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

    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id,
                           Model model){
        logger.trace("/edit");


        return "riddlesList";
    }
}
