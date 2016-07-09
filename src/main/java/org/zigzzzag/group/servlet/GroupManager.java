package org.zigzzzag.group.servlet;

import com.google.appengine.repackaged.com.google.common.base.StringUtil;
import org.zigzzzag.group.model.VkGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sbt-nikiforov-mo on 30.06.16.
 */
public class GroupManager extends HttpServlet {

    private static final String GROUPS_GET_MEMBERS = "https://api.vk.com/method/groups.getMembers?group_id=";
    private static final String DODO_BERDSK_GROUP_ID = "dodo_berdsk";

    public static volatile Set<VkGroup> VK_GROUPS = new HashSet<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupId = req.getParameter("groupId");
        String addGroup = req.getParameter("addGroup");
        String deleteGroup = req.getParameter("deleteGroup");

        if (!StringUtil.isEmptyOrWhitespace(groupId)) {
            VkGroup vkGroup = new VkGroup(groupId);
            if (!StringUtil.isEmptyOrWhitespace(addGroup)) {
                VK_GROUPS.add(vkGroup);
            } else if (!StringUtil.isEmptyOrWhitespace(deleteGroup)) {
                VK_GROUPS.remove(vkGroup);
            }
        }

        req.setAttribute("vkGroups", vkGroupsToString());
        RequestDispatcher view = req.getRequestDispatcher("group_manage.jsp");
        view.forward(req, resp);
    }

    private Set<String> vkGroupsToString() {
        Set<String> res = new HashSet<>();
        for (VkGroup vkGroup : VK_GROUPS) {
            res.add(vkGroup.getId());
        }
        return res;
    }
}
