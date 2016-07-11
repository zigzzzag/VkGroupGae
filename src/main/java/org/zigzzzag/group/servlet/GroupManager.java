package org.zigzzzag.group.servlet;

import com.google.appengine.repackaged.com.google.common.base.StringUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by sbt-nikiforov-mo on 30.06.16.
 */
public class GroupManager extends HttpServlet {

    public static final Set<String> VK_GROUPS_ID = new CopyOnWriteArraySet<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupId = req.getParameter("groupId");
        String addGroup = req.getParameter("addGroup");
        String deleteGroup = req.getParameter("deleteGroup");

        if (!StringUtil.isEmptyOrWhitespace(groupId)) {
            if (!StringUtil.isEmptyOrWhitespace(addGroup)) {
                VK_GROUPS_ID.add(groupId);
            } else if (!StringUtil.isEmptyOrWhitespace(deleteGroup)) {
                VK_GROUPS_ID.remove(groupId);
            }
        }

        req.setAttribute("vkGroups", VK_GROUPS_ID);
        RequestDispatcher view = req.getRequestDispatcher("group_manage.jsp");
        view.forward(req, resp);
    }
}
