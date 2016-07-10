package org.zigzzzag.group.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.zigzzzag.group.model.GroupsGetMembersResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zigzag on 10.07.2016.
 */
public class GroupsGetMembers {

    private static final int MAX_MEMBERS_COUNT_RESPONSE = 1000;
    private static final String GROUPS_GET_MEMBERS = "https://api.vk.com/method/groups.getMembers?group_id=%s&offset=%s";
    private static final String DODO_BERDSK_GROUP_ID = "dodo_berdsk";

    public static String getQuery(String groupId, int offset) {
        return String.format(GROUPS_GET_MEMBERS, groupId, offset);
    }

    public static GroupsGetMembersResponse getAllMembers(String groupId) throws IOException {
        GroupsGetMembersResponse firstResp = getAllMembers(groupId, 0);

        //TODO remake on exception
        if (firstResp.getCount() > 30_000) {
            return null;
        }

        Set<GroupsGetMembersResponse> otherResp = new HashSet<>();
        for (int i = MAX_MEMBERS_COUNT_RESPONSE; i < firstResp.getCount(); i += MAX_MEMBERS_COUNT_RESPONSE) {
            otherResp.add(getAllMembers(groupId, i));
        }

        GroupsGetMembersResponse result = firstResp;
        for (GroupsGetMembersResponse resp : otherResp) {
            result.getUsers().addAll(resp.getUsers());
        }

        return result;
    }

    private static GroupsGetMembersResponse getAllMembers(String groupId, int offset) throws IOException {
        GroupsGetMembersResponse result;

        String url = getQuery(groupId, offset);
        HttpGet request = new HttpGet(url);

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
            StringBuffer resultStrBuf = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                resultStrBuf.append(line);
            }

            result = GroupsGetMembersResponse.fromVkJson(resultStrBuf.toString());
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            GroupsGetMembersResponse allMembers = getAllMembers("xpywa");
            System.out.println("alltime: " + (System.currentTimeMillis() - start));
            System.out.println(allMembers.getCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
