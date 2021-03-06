package org.zigzzzag.group.task;

import com.google.appengine.repackaged.org.joda.time.LocalDate;
import org.zigzzzag.group.data.AllGroupData;
import org.zigzzzag.group.model.GroupsGetMembers;
import org.zigzzzag.group.servlet.GroupManager;
import org.zigzzzag.group.util.GroupsGetMembersUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zigzag on 09.07.2016.
 */
public class VkGroupTask  {

    public static final VkGroupTask INSTANCE = new VkGroupTask();

    public void execute() {
        if (!GroupManager.VK_GROUPS_ID.isEmpty()) {
            for (String groupId : GroupManager.VK_GROUPS_ID) {
                try {
                    GroupsGetMembers response = GroupsGetMembersUtil.getAllMembers(groupId);

                    storeGroupDataByDate(groupId, response);

                    AllGroupData.GroupData groupData = AllGroupData.INSTANCE.getGroupData(new LocalDate(), groupId);
                    GroupsGetMembers storedMembers = groupData.getGroup();

                    Set<Integer> added = getAddedUserIds(storedMembers, response);
                    groupData.getAddedGroupIds().addAll(added);

                    Set<Integer> deleted = getDeletedUserIds(storedMembers, response);
                    groupData.getDeletedGroupIds().addAll(deleted);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Set<Integer> getAddedUserIds(GroupsGetMembers storedMembers, GroupsGetMembers newMembers) {
        Set<Integer> result = new HashSet<>();

        for (Integer newId : newMembers.getUsers()) {
            if (!storedMembers.getUsers().contains(newId)) {
                result.add(newId);
            }
        }

        return result;
    }

    private Set<Integer> getDeletedUserIds(GroupsGetMembers storedMembers, GroupsGetMembers newMembers) {
        Set<Integer> result = new HashSet<>();

        for (Integer newId : storedMembers.getUsers()) {
            if (!newMembers.getUsers().contains(newId)) {
                result.add(newId);
            }
        }

        return result;
    }

    private void storeGroupDataByDate(String groupId, GroupsGetMembers response) {
        AllGroupData.GroupData groupData = AllGroupData.INSTANCE.getGroupData(new LocalDate(), groupId);
        if (groupData == null) {
            AllGroupData.GroupData newGroupData = new AllGroupData.GroupData(new LocalDate(), response);
            AllGroupData.INSTANCE.GROUP_DATA_SET.add(newGroupData);
        }
    }
}
