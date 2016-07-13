package org.zigzzzag.group.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sbt-nikiforov-mo on 11.07.16.
 */
public class GroupReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("groupIds", GroupManager.VK_GROUPS_ID);

        RequestDispatcher view = req.getRequestDispatcher("group_report.jsp");
        view.forward(req, resp);
    }
}
