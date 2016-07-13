package src.test.org.zigzzzag.group.data;

import com.google.appengine.repackaged.org.joda.time.LocalDate;
import junit.framework.TestCase;
import org.zigzzzag.group.data.AllGroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sbt-nikiforov-mo on 13.07.16.
 */
public class AllGroupDataTest extends TestCase {

    public void testClearOld() {
        Set<AllGroupData.GroupData> groupDatas = AllGroupData.INSTANCE.GROUP_DATA_SET;
        groupDatas.add(new AllGroupData.GroupData(new LocalDate(2014, 1, 1)));
        groupDatas.add(new AllGroupData.GroupData(new LocalDate(1970, 1, 1)));
        groupDatas.add(new AllGroupData.GroupData(new LocalDate(2018, 1, 1)));
        groupDatas.add(new AllGroupData.GroupData(new LocalDate()));
        groupDatas.add(new AllGroupData.GroupData(new LocalDate().minusDays(7)));
        groupDatas.add(new AllGroupData.GroupData(new LocalDate(1969, 1, 1)));

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
