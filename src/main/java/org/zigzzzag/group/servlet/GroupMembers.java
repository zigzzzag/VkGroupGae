package org.zigzzzag.group.servlet;

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
public class GroupMembers extends HttpServlet {

    private static final String GROUPS_GET_MEMBERS = "https://api.vk.com/method/groups.getMembers?group_id=";
    private static final String DODO_BERDSK_GROUP_ID = "dodo_berdsk";

    public static volatile Set<VkGroup> VK_GROUPS = new HashSet<>();

    static int count = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(count++);

        String groupId = req.getParameter("groupId");
        if (groupId != null) {
            VkGroup newVkGroup = new VkGroup(groupId);
            VK_GROUPS.add(newVkGroup);
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
