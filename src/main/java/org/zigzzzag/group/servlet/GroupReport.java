package org.zigzzzag.group.servlet;

import com.google.appengine.repackaged.com.google.common.base.StringUtil;
import com.google.appengine.repackaged.org.joda.time.LocalDate;
import com.google.appengine.repackaged.org.joda.time.format.DateTimeFormat;
import com.google.appengine.repackaged.org.joda.time.format.DateTimeFormatter;
import org.zigzzzag.group.data.AllGroupData;

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

        String dateStr = req.getParameter("date");
        String groupId = req.getParameter("groupId");

        if (!StringUtil.isEmptyOrWhitespace(dateStr) && !StringUtil.isEmptyOrWhitespace(groupId)) {
            final DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
            LocalDate date = dtf.parseLocalDate(dateStr);

            AllGroupData.GroupData groupData = AllGroupData.INSTANCE.getGroupData(date, groupId);
            if (groupData != null) {
                req.setAttribute("addedIds", groupData.getAddedGroupIds());
                req.setAttribute("deletedIds", groupData.getDeletedGroupIds());
            }
        }

        req.setAttribute("date", dateStr);
        req.setAttribute("groupIds", GroupManager.VK_GROUPS_ID);

        RequestDispatcher view = req.getRequestDispatcher("group_report.jsp");
        view.forward(req, resp);
    }
}
