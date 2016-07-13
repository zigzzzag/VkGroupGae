package org.zigzzzag.group.servlet;

import com.google.appengine.repackaged.org.joda.time.LocalDate;
import com.google.appengine.repackaged.org.joda.time.LocalDateTime;
import org.zigzzzag.group.model.GroupsGetMembers;
import org.zigzzzag.group.util.GroupsGetMembersUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Zigzag on 14.07.2016.
 */
public class UnloadGroupData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String groupId = req.getParameter("groupId");

        if (groupId != null) {
            GroupsGetMembers groupMembers = GroupsGetMembersUtil.getAllMembers(groupId);
            if (groupMembers != null) {
                resp.setContentType("text/csv");
                String fileName = groupId + new LocalDate().toString();
                resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".csv\"");


                try (OutputStream outputStream = resp.getOutputStream()) {
                    outputStream.write((groupId + "\r\n").getBytes());
                    outputStream.write((new LocalDateTime().toString() + "\r\n\r\n").getBytes());

                    StringBuilder sb = new StringBuilder();
                    for (Integer memberId : groupMembers.getUsers()) {
                        sb.append(memberId).append("\r\n");
                    }
                    outputStream.write(sb.toString().getBytes());
                }
            }
        }
    }
}
