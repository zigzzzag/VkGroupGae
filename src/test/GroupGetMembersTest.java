import junit.framework.TestCase;
import org.zigzzzag.group.model.GroupsGetMembersResponse;

/**
 * Created by Zigzag on 09.07.2016.
 */
public class GroupGetMembersTest extends TestCase {

    public void testGroupGetMembers() {
        String testJson = "{\"count\":1676," +
                "\"users\":[46481,181495,212086,215268,367386]" +
                "}";

        GroupsGetMembersResponse expected = new GroupsGetMembersResponse();
        expected.setCount(1676);
        expected.setUsers(new int[]{46481, 181495, 212086, 215268, 367386});

        GroupsGetMembersResponse actual = GroupsGetMembersResponse.fromJson(testJson);


        assertEquals(expected, actual);
    }
}
