package controllers.servlets.common;

import common.exceptions.UserDaoException;
import models.pojo.User;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by yuri on 23.02.17.
 */
public class LoginServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);

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
            User user = UserService.authorize(login, password);

            if (user.getIdUser() != 0) {
                logger.trace("authorized and in session set attribute");

                session.setAttribute("id", user.getIdUser());
                session.setAttribute("admin", user.isAdminTrue());

                if (user.isAdminTrue()) session.setAttribute("notification", user);

                session.setMaxInactiveInterval(30*60);

                if (user.isAdminTrue()) resp.sendRedirect("/adminDashboard");
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

