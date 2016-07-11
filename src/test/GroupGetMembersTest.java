package src.test;

import junit.framework.TestCase;
import org.zigzzzag.group.model.GroupsGetMembersResponse;
import org.zigzzzag.group.util.GroupsGetMembers;

import java.util.Arrays;

/**
 * Created by Zigzag on 09.07.2016.
 */
public class GroupGetMembersTest extends TestCase {

    public void testParseGroupGetMembers() {
        String testJson = "{\"count\":1676," +
                "\"users\":[46481,181495,212086,215268,367386]" +
                "}";

        GroupsGetMembersResponse expected = new GroupsGetMembersResponse();
        expected.setCount(1676);
        expected.setUsers(Arrays.asList(46481, 181495, 212086, 215268, 367386));

        GroupsGetMembersResponse actual = GroupsGetMembersResponse.fromJson(testJson);

        assertEquals(expected, actual);
    }

    public void testVkResponseGetMembers() {
        String testJson = "{\"response\":" +
                "{\"count\":1676," +
                "\"users\":[46481,181495,212086,215268,367386]" +
                "}}";

        GroupsGetMembersResponse expected = new GroupsGetMembersResponse();
        expected.setCount(1676);
        expected.setUsers(Arrays.asList(46481, 181495, 212086, 215268, 367386));

        GroupsGetMembersResponse actual = GroupsGetMembersResponse.fromVkJson(testJson);

        assertEquals(expected, actual);
    }

    public void testGetQuery() {
        String actual = GroupsGetMembers.getQuery("qqq", 234);
        String expected = "https://api.vk.com/method/groups.getMembers?group_id=qqq&offset=234";

        assertEquals(expected, actual);
    }
}
