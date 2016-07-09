package org.zigzzzag.group.task;

import org.zigzzzag.group.model.VkGroup;
import org.zigzzzag.group.servlet.GroupManager;

/**
 * Created by Zigzag on 09.07.2016.
 */
public class VkGroupTask implements Runnable {

    @Override
    public void run() {
        if (!GroupManager.VK_GROUPS.isEmpty()) {
            for (VkGroup vkGroup : GroupManager.VK_GROUPS) {
                System.out.print(vkGroup.getId());
            }
            System.out.println();
        }
    }
}
