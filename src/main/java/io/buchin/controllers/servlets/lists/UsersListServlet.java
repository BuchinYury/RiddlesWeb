package io.buchin.controllers.servlets.lists;


import io.buchin.common.exceptions.UserDaoException;
import io.buchin.models.pojo.User;
import org.apache.log4j.Logger;
import io.buchin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yuri on 25.02.17.
 */
public class UsersListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UsersListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("UsersListServlet GET");

        List<User> usersList = null;
        try {
            usersList = UserService.getAllUsers();
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/error.jsp");
        }
        req.setAttribute("usersList", usersList);
        req.getRequestDispatcher("WEB-INF/jsp/lists/usersList.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
