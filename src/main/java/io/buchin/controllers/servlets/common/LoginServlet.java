package io.buchin.controllers.servlets.common;

import io.buchin.common.exceptions.UserDaoException;
import io.buchin.controllers.servlets.FatherServlets;
import io.buchin.models.pojo.User;
import org.apache.log4j.Logger;
import io.buchin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by yuri on 23.02.17.
 */
public class LoginServlet extends FatherServlets {
    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("LoginServlet GET");
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("LoginServlet POST");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        try {
            User user = userService.authorize(login, password);

            if (user.getIdUser() != 0) {
                logger.trace("authorized and in session set attribute");

                session.setMaxInactiveInterval(30 * 60);
                session.setAttribute("id", user.getIdUser());
                session.setAttribute("admin", user.isAdminTrue());

                if (user.isAdminTrue()) {
//                    session.setAttribute("mailTo", user);
//                    session.setAttribute("notification", user.isNotification());
                    resp.sendRedirect("/adminDashboard");
                }
                else resp.sendRedirect("/userDashboard");

            } else {
                logger.trace("not authorized");
                session.setAttribute("id", null);
                resp.sendRedirect("/login");
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/error.jsp");
        }
    }
}

