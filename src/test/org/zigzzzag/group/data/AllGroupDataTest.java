package src.test.org.zigzzzag.group.data;

import com.google.appengine.repackaged.org.joda.time.LocalDate;
import junit.framework.TestCase;
import org.zigzzzag.group.data.AllGroupData;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sbt-nikiforov-mo on 13.07.16.
 */
public class AllGroupDataTest extends TestCase {

    public void testClearOld() {
        Set<AllGroupData.GroupData> groupDatas = AllGroupData.INSTANCE.GROUP_DATA_SET;
        groupDatas.add(new AllGroupData.GroupData(new LocalDate(2014, 1, 1), null));
        groupDatas.add(new AllGroupData.GroupData(new LocalDate(1970, 1, 1), null));
        groupDatas.add(new AllGroupData.GroupData(new LocalDate(2018, 1, 1), null));
        groupDatas.add(new AllGroupData.GroupData(new LocalDate(), null));
        groupDatas.add(new AllGroupData.GroupData(new LocalDate().minusDays(7), null));
        groupDatas.add(new AllGroupData.GroupData(new LocalDate(1969, 1, 1), null));

        AllGroupData.INSTANCE.clearOldData();

        Set<LocalDate> actual = new HashSet<>();
        for (AllGroupData.GroupData groupData : groupDatas) {
            actual.add(groupData.getDate());
        }

        Set<LocalDate> expected = new HashSet<>();
        expected.add(new LocalDate(2018, 1, 1));
        expected.add(new LocalDate());
        expected.add(new LocalDate().minusDays(7));

        assertEquals(expected, actual);
    }
}
