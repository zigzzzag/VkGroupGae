package org.zigzzzag.group.data;

import com.google.appengine.repackaged.org.joda.time.LocalDate;
import org.zigzzzag.group.model.GroupsGetMembers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public GroupData getGroupDataByDate(LocalDate date) {
        for (GroupData gd : GROUP_DATA_SET) {
            if (date.equals(gd.getDate())) {
                return gd;
            }
        }

        return null;
    }


    public static class GroupData {
        private final LocalDate date;
        private List<GroupsGetMembers> groups = new ArrayList<>();
        private Set<Integer> addedGroupIds = new HashSet<>();
        private Set<Integer> deletedGroupIds = new HashSet<>();

        public GroupData(LocalDate date) {
            this.date = date;
        }

        public GroupsGetMembers getGroupGetMembers(String id) {
            for (GroupsGetMembers ggm : groups) {
                if (id.equals(ggm.getId())) {
                    return ggm;
                }
            }
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GroupData)) return false;

            GroupData groupData = (GroupData) o;

            return date.equals(groupData.date);

        }

        @Override
        public int hashCode() {
            return date.hashCode();
        }

        public List<GroupsGetMembers> getGroups() {
            return groups;
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
