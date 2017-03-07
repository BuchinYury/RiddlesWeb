package io.buchin.controllers.mvccontrollers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yuri on 07.03.17.
 */

@Controller
public class LogoutController {
    private static Logger logger = Logger.getLogger(LogoutController.class);

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String showBasePage() {
        logger.trace("LogoutController - LOGOUT");

        return "redirect:/login";
    }

}
