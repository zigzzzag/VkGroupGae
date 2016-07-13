package org.zigzzzag.group.model;


import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.appengine.repackaged.com.google.gson.JsonParser;

import java.util.Set;

/**
 * Created by Zigzag on 09.07.2016.
 */
public class GroupsGetMembers {

    private String id;
    private int count;
    private Set<Integer> users;

    public GroupsGetMembers() {
    }

    public static GroupsGetMembers fromJson(String json) {
        Gson g = new Gson();
        GroupsGetMembers result = g.fromJson(json, GroupsGetMembers.class);
        return result;
    }

    public static GroupsGetMembers fromVkJson(String json) {
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(json).getAsJsonObject();

        //TODO handle
        if (obj.get("response") == null) return null;

        String response = obj.get("response").toString();

        return fromJson(response);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupsGetMembers)) return false;

        GroupsGetMembers that = (GroupsGetMembers) o;

        if (count != that.count) return false;
        return users != null ? users.equals(that.users) : that.users == null;
    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<Integer> getUsers() {
        return users;
    }

    public void setUsers(Set<Integer> users) {
        this.users = users;
    }
}
