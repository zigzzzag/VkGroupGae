import junit.framework.TestCase;
import org.zigzzzag.group.model.GroupsGetMembersResponse;

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
}