package org.zigzzzag.group.util;

import org.zigzzzag.group.model.GroupsGetMembersResponse;

/**
 * Created by Zigzag on 10.07.2016.
 */
public class GroupsGetMembers {

    private static final String GROUPS_GET_MEMBERS = "https://api.vk.com/method/groups.getMembers?group_id=%s&offset=%s";
    private static final String DODO_BERDSK_GROUP_ID = "dodo_berdsk";

    public static GroupsGetMembersResponse getRespByGroupId(String groupId) {
        return null;
    }

    public static String getQuery(String groupId, int offset) {
        return String.format(GROUPS_GET_MEMBERS, groupId, offset);
    }
}
