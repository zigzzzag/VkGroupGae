package org.zigzzzag.group.servlet;

import org.zigzzzag.group.data.AllGroupData;
import org.zigzzzag.group.task.VkGroupTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sbt-nikiforov-mo on 15.07.16.
 */
public class RefreshData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AllGroupData.INSTANCE.clearOldData();
        VkGroupTask.INSTANCE.execute();
        req.setAttribute("actualDate", AllGroupData.INSTANCE.getActualDate());

        RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);
    }
}
