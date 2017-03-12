package io.buchin.controllers.servlets.edits;

import io.buchin.common.exceptions.RiddleDaoException;
import io.buchin.controllers.servlets.FatherServlets;
import io.buchin.models.pojo.Riddle;
import io.buchin.services.IRiddleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuri on 28.02.17.
 */
public class RiddleEditServlet extends FatherServlets {
    private static Logger logger = Logger.getLogger(RiddleEditServlet.class);

    @Autowired
    private IRiddleService riddleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("RiddleEditServlet GET");

        int id = Integer.parseInt(req.getParameter("id"));

        Riddle riddle = null;

        try {
            riddle = riddleService.getRiddleById(id);
            logger.trace(riddle.getIdRiddle());
        } catch (RiddleDaoException e) {
            logger.error(e);
        }

        logger.trace(riddle);

        req.setAttribute("idRiddle", riddle.getIdRiddle());
        req.setAttribute("name", riddle.getName());
        req.setAttribute("text", riddle.getText());
        req.setAttribute("answer", riddle.getAnswer());
        req.setAttribute("level", riddle.getLevel());
        req.setAttribute("category", riddle.getCategory());
        req.setAttribute("idUser", riddle.getIdUser());
        req.setAttribute("block", riddle.isBlock());

        req.getRequestDispatcher("WEB-INF/jsp/edits/editRiddle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("RiddleEditServlet POST");

        String strId = req.getParameter("idRiddle");
        String strIsBlock = req.getParameter("isBlock");

        int id = Integer.parseInt(strId);
        boolean isBlock = Boolean.parseBoolean(strIsBlock);

        try {
            if (isBlock) riddleService.blockOrUnblockRiddle(id, 0);
            else riddleService.blockOrUnblockRiddle(id, 1);

        } catch (RiddleDaoException e) {
            logger.error(e);
        }

//        req.getRequestDispatcher("/riddlesList").forward(req, resp);
        resp.sendRedirect("/riddlesList");
    }
}
