package io.buchin.controllers.servlets.dashboards;

import io.buchin.common.exceptions.UserDaoException;
import org.apache.log4j.Logger;
import io.buchin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yuri on 26.02.17.
 */
public class AdminDashboardServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AdminDashboardServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("AdminDashboardServlet GET");
        HttpSession session = req.getSession();

        if ((boolean) session.getAttribute("admin")) {
//            try {
//                session.setAttribute("notification", UserService.getUserById((int) session.getAttribute("id")));
//            } catch (UserDaoException e) {
//                logger.error(e);
//            }
//            User user = (User) session.getAttribute("notification");
//            session.setAttribute("notification", user.isNotification());
            req.getRequestDispatcher("WEB-INF/jsp/dashboards/adminDashboard.jsp").forward(req, resp);
        } else resp.sendRedirect("/userDashboard");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("AdminDashboardServlet POST");

        HttpSession session = req.getSession();

        int id = (int) session.getAttribute("id");

        String strIsNotification = req.getParameter("Notification");

        boolean isNotification = Boolean.parseBoolean(strIsNotification);

        logger.trace("AdminDashboardServlet POST - " + isNotification);

        try {
            if (isNotification){
                UserService.sendNotification(id, 1);
                session.setAttribute("notification", true);
            }
            else {
                UserService.sendNotification(id, 0);
                session.setAttribute("notification", false);
            }

        } catch (UserDaoException e) {
            logger.error(e);
        }

        resp.sendRedirect("/adminDashboard");
    }
}
