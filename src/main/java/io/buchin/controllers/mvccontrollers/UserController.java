package io.buchin.controllers.mvccontrollers;

import io.buchin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by yuri on 07.03.17.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;



}
