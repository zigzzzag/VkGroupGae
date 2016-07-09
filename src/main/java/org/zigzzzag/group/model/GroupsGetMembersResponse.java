package org.zigzzzag.group.model;


import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.appengine.repackaged.com.google.gson.JsonParser;

import java.util.List;

/**
 * Created by Zigzag on 09.07.2016.
 */
public class GroupsGetMembersResponse {

    private int count;
    private List<Integer> users;

    public GroupsGetMembersResponse() {
    }

    public static GroupsGetMembersResponse fromJson(String json) {
        Gson g = new Gson();
        GroupsGetMembersResponse result = g.fromJson(json, GroupsGetMembersResponse.class);
        return result;
    }

    public static GroupsGetMembersResponse fromVkJson(String json) {
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(json).getAsJsonObject();
        String response = obj.get("response").toString();

        return fromJson(response);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupsGetMembersResponse)) return false;

        GroupsGetMembersResponse that = (GroupsGetMembersResponse) o;

        if (count != that.count) return false;
        return users != null ? users.equals(that.users) : that.users == null;
    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }
}
