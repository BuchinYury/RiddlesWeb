package controllers.servlets.dashboards;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yuri on 26.02.17.
 */
public class UserDashboardServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UserDashboardServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("UserDashboardServlet GET");
        HttpSession session = req.getSession();
        if (!(boolean) session.getAttribute("admin")) {
            req.getRequestDispatcher("WEB-INF/jsp/userDashboard.jsp").forward(req, resp);
        } else  resp.sendRedirect("/adminDashboard");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
