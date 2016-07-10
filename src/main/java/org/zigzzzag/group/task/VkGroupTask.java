package org.zigzzzag.group.task;

import org.zigzzzag.group.model.GroupsGetMembersResponse;
import org.zigzzzag.group.model.VkGroup;
import org.zigzzzag.group.servlet.GroupManager;
import org.zigzzzag.group.util.GroupsGetMembers;

import java.io.IOException;

/**
 * Created by Zigzag on 09.07.2016.
 */
public class VkGroupTask implements Runnable {



    @Override
    public void run() {
        if (!GroupManager.VK_GROUPS.isEmpty()) {
            for (VkGroup vkGroup : GroupManager.VK_GROUPS) {
                try {
                    GroupsGetMembersResponse response = GroupsGetMembers.getAllMembers(vkGroup.getId());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
