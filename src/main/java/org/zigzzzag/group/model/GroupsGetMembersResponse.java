package org.zigzzzag.group.model;


import com.google.appengine.repackaged.com.google.gson.Gson;

import java.util.Arrays;

/**
 * Created by Zigzag on 09.07.2016.
 */
public class GroupsGetMembersResponse {

    private int count;
    private int[] users;

    public GroupsGetMembersResponse() {
    }

    public static GroupsGetMembersResponse fromJson(String json) {
        Gson g = new Gson();
        GroupsGetMembersResponse result = g.fromJson(json, GroupsGetMembersResponse.class);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupsGetMembersResponse)) return false;

        GroupsGetMembersResponse that = (GroupsGetMembersResponse) o;

        if (count != that.count) return false;
        return Arrays.equals(users, that.users);

    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + Arrays.hashCode(users);
        return result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int[] getUsers() {
        return users;
    }

    public void setUsers(int[] users) {
        this.users = users;
    }
}
