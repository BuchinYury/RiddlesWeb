package controllers.servlets.common;

import common.exceptions.UserDaoException;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuri on 24.02.17.
 */
public class RegistrationServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("RegistrationServlet GET");
        req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("RegistrationServlet POST");

        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");

        try {
            if (UserService.registration(login, pass, userName, email)) {
                logger.trace("Зарегистрирован новый пользователь");
                resp.sendRedirect("/login");
            } else {
                logger.trace("Неудалось зарегистрировать нового пользователя");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        } catch (UserDaoException e) {
            logger.error(e);
            logger.error("Произошла ошибка при взаимодействии с DB users");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
