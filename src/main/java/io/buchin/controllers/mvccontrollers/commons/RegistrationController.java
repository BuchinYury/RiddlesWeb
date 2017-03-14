package io.buchin.controllers.mvccontrollers.commons;

import io.buchin.common.exceptions.UserDaoException;
import io.buchin.common.hash.HashPass;
import io.buchin.services.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by yuri on 14.03.17.
 */
@Controller
public class RegistrationController {
    private static Logger logger = Logger.getLogger(RegistrationController.class);

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationPage() {
        return "main/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationUser(HttpSession session,
                                   Model model,
                                   @RequestParam(value = "login") String login,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "userName") String userName,
                                   @RequestParam(value = "email") String email) {

        logger.trace("registration POST");

        try {
            if (login.isEmpty() ||
                    password.isEmpty() ||
                    userName.isEmpty() ||
                    email.isEmpty()) {
                logger.trace("Поля не заполнены");

                model.addAttribute("mes", "Вы не заполнили поля формы регистрации");
                return "main/registration";
            }
            if (userService.findLogin(login)) {
                logger.trace("Проверка на дубликат логина");

                model.addAttribute("mesLogin", "Пользователь с таким логином уже зарегестрирован в системе");
                return "main/registration";
            }
        } catch (UserDaoException e) {
            logger.error(e);
            logger.trace("Перехвачен дубликат");
//            return "main/registration";
            return "redirect:/error";
        }

        String pass = HashPass.passToDB(password);
        if (pass.isEmpty()) return "redirect:/error";

        try {
            if (userService.registration(login, pass, userName, email)) {
                logger.trace("Зарегистрирован новый пользователь");
                return "redirect:/login";
            } else {
                logger.trace("Неудалось зарегистрировать нового пользователя");
                return "redirect:/error";
            }
        } catch (UserDaoException e) {
            logger.error(e);
            return "redirect:/error";
        }
    }
}
