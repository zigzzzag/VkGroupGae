package org.zigzzzag.group.data;

import com.google.appengine.repackaged.org.joda.time.LocalDate;
import org.zigzzzag.group.model.GroupsGetMembers;

import java.security.acl.Group;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by sbt-nikiforov-mo on 11.07.16.
 */
public class AllGroupData {

    public static AllGroupData INSTANCE = new AllGroupData();
    public final Set<GroupData> GROUP_DATA_SET = new CopyOnWriteArraySet<>();

    private AllGroupData() {
    }

    public LocalDate getActualDate() {
        if (GROUP_DATA_SET.isEmpty()) return null;

        LocalDate maxDate = null;
        for (GroupData gd : GROUP_DATA_SET) {
            if (maxDate == null) {
                maxDate = gd.getDate();
                continue;
            }
            if (maxDate.isBefore(gd.getDate())) {
                maxDate = gd.getDate();
            }
        }
        return maxDate;
    }

    /**
     * Удаляет группы созданные более 7 дней назад
     */
    public void clearOldData() {
        LocalDate minDate = new LocalDate().minusDays(7);
        for (GroupData gd : GROUP_DATA_SET) {
            if (gd.getDate().compareTo(minDate) < 0) {
                GROUP_DATA_SET.remove(gd);
            }
        }
    }

    public GroupData getGroupData(LocalDate date, String groupId) {
        for (GroupData gd : GROUP_DATA_SET) {
            if (date.equals(gd.getDate()) && gd.getGroup().getId().equals(groupId)) {
                return gd;
            }
        }

        return null;
    }


    public static class GroupData {
        private final LocalDate date;
        private final GroupsGetMembers group;
        private Set<Integer> addedGroupIds = new HashSet<>();
        private Set<Integer> deletedGroupIds = new HashSet<>();

        public GroupData(LocalDate date, GroupsGetMembers group) {
            this.date = date;
            this.group = group;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GroupData)) return false;

            GroupData groupData = (GroupData) o;

            if (!date.equals(groupData.date)) return false;
            return group != null ? group.equals(groupData.group) : groupData.group == null;

        }

        @Override
        public int hashCode() {
            int result = date.hashCode();
            result = 31 * result + (group != null ? group.hashCode() : 0);
            return result;
        }

        public GroupsGetMembers getGroup() {
            return group;
        }

        public Set<Integer> getAddedGroupIds() {
            return addedGroupIds;
        }

        public Set<Integer> getDeletedGroupIds() {
            return deletedGroupIds;
        }

        public LocalDate getDate() {
            return date;
        }
    }
}
