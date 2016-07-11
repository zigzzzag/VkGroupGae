package org.zigzzzag.group.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zigzag on 10.07.2016.
 */
public class GroupsGetMembersUtil {

    private static final int MAX_MEMBERS_COUNT_RESPONSE = 1000;
    private static final String GROUPS_GET_MEMBERS = "https://api.vk.com/method/groups.getMembers?group_id=%s&offset=%s";
    private static final String DODO_BERDSK_GROUP_ID = "dodo_berdsk";

    public static String getQuery(String groupId, int offset) {
        return String.format(GROUPS_GET_MEMBERS, groupId, offset);
    }

    public static org.zigzzzag.group.model.GroupsGetMembers getAllMembers(String groupId) throws IOException {
        org.zigzzzag.group.model.GroupsGetMembers firstResp = getAllMembers(groupId, 0);

        //TODO remake on exception
        if (firstResp.getCount() > 30_000) {
            return null;
        }

        Set<org.zigzzzag.group.model.GroupsGetMembers> otherResp = new HashSet<>();
        for (int i = MAX_MEMBERS_COUNT_RESPONSE; i < firstResp.getCount(); i += MAX_MEMBERS_COUNT_RESPONSE) {
            otherResp.add(getAllMembers(groupId, i));
        }

        org.zigzzzag.group.model.GroupsGetMembers result = firstResp;
        for (org.zigzzzag.group.model.GroupsGetMembers resp : otherResp) {
            result.getUsers().addAll(resp.getUsers());
        }

        return result;
    }

    private static org.zigzzzag.group.model.GroupsGetMembers getAllMembers(String groupId, int offset) throws IOException {
        org.zigzzzag.group.model.GroupsGetMembers result;

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

            result = org.zigzzzag.group.model.GroupsGetMembers.fromVkJson(resultStrBuf.toString());
            result.setId(groupId);
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            org.zigzzzag.group.model.GroupsGetMembers allMembers = getAllMembers("xpywa");
            System.out.println("alltime: " + (System.currentTimeMillis() - start));
            System.out.println(allMembers.getCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
