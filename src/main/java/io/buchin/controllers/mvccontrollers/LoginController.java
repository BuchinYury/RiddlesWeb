package io.buchin.controllers.mvccontrollers;

import io.buchin.common.exceptions.UserDaoException;
import io.buchin.controllers.servlets.common.LoginServlet;
import io.buchin.models.pojo.User;
import io.buchin.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by yuri on 07.03.17.
 */

@Controller
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

//    @Autowired
//    public void setUserService(IUserService userService) {
//        this.userService = userService;
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBasePage() {
        return "start";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationPage() {
        return "main/registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "main/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(HttpSession session,
                            @RequestParam(value = "login") String login,
                            @RequestParam(value = "password") String password) {
        logger.trace("/login POST");

        try {
            User user = userService.authorize(login, password);

            if (user.getIdUser() != 0) {
                logger.trace("authorized and in session set attribute");

                session.setMaxInactiveInterval(30 * 60);
                session.setAttribute("id", user.getIdUser());
                session.setAttribute("admin", user.isAdminTrue());

                logger.trace(user.getLogin());
                logger.trace(user.isAdminTrue());

                return "redirect:/dashboard";
            } else {
                logger.trace("not authorized");
                session.setAttribute("id", null);
                return "/login";
            }
        } catch (UserDaoException e) {
            logger.error(e);
            return "/error.jsp";
        }
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(HttpSession session) {
        logger.trace("/dashboard GET");
        logger.trace(session.getAttribute("admin"));

        if ((boolean)session.getAttribute("admin")){
            logger.trace("admin");
            return "dashboards/adminDashboard";
        } else{
            logger.trace("user");
            return "dashboards/userDashboard";
        }
    }


}
