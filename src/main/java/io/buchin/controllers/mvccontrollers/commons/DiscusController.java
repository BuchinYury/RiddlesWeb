package io.buchin.controllers.mvccontrollers.commons;

import io.buchin.common.exceptions.DiscusDaoException;
import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.models.pojo.Discus;
import io.buchin.services.IDiscusService;
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
 * Created by yuri on 13.03.17.
 */
@Controller
public class DiscusController {
    private static Logger logger = Logger.getLogger(DiscusController.class);

    private IRiddleService riddleService;

    @Autowired
    public void setRiddleService(IRiddleService riddleService) {
        this.riddleService = riddleService;
    }


    private IDiscusService discusService;

    @Autowired
    public void setRiddleService(IDiscusService discusService) {
        this.discusService = discusService;
    }

    @RequestMapping(value = "/discusRiddle/{id}", method = RequestMethod.GET)
    public String discusRiddle(@PathVariable("id") int id,
                               Model model) {
        logger.trace("/discusRiddle");

        try {
            model.addAttribute("DiscusList", discusService.getDiscusByRiddleId(id));
            model.addAttribute("RiddleID", id);
            model.addAttribute("RiddleName", riddleService.getRiddleById(id).getName());

        } catch (DiscusDaoException | RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "commons/discusList";
    }

    @RequestMapping(value = "/discusRiddle/{id}", method = RequestMethod.POST)
    public String discusRiddle(HttpSession session,
                               @PathVariable("id") int id,
                               Model model,
                               @ModelAttribute("discus") String textDiscus) {
        logger.trace("/discusRiddle");

        try {

            discusService.addDiscus(new Discus(0,
                    (int) session.getAttribute("id"),
                    "",
                    id,
                    textDiscus));

            model.addAttribute("DiscusList", discusService.getDiscusByRiddleId(id));
            model.addAttribute("RiddleID", id);
            model.addAttribute("RiddleName", riddleService.getRiddleById(id).getName());
        } catch (DiscusDaoException | RiddleDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }

        return "commons/discusList";
    }
}
