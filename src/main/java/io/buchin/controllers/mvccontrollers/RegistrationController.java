package io.buchin.controllers.mvccontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yuri on 14.03.17.
 */
@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationPage() {
        return "main/registration";
    }
}
