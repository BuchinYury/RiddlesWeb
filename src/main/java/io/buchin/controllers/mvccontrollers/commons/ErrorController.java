package io.buchin.controllers.mvccontrollers.commons;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yuri on 14.03.17.
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String showLoginPage() {
        return "error";
    }
}
