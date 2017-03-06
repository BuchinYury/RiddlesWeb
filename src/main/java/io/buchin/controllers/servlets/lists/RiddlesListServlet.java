package io.buchin.controllers.servlets.lists;

import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.models.pojo.Riddle;
import org.apache.log4j.Logger;
import io.buchin.services.RiddleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yuri on 27.02.17.
 */
public class RiddlesListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RiddlesListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("RiddlesListServlet GET");

        List<Riddle> riddlesList = null;
        try {
            riddlesList = RiddleService.getAllRiddles();
        } catch (RiddleDaoException e) {
            logger.error(e);
            resp.sendRedirect("/error.jsp");
        }

        req.setAttribute("riddlesList", riddlesList);
        req.getRequestDispatcher("WEB-INF/jsp/lists/riddlesList.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
