package org.zigzzzag.group.model;

/**
 * Created by Zigzag on 09.07.2016.
 */
public class VkGroup {

    private String id;

    public VkGroup(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VkGroup)) return false;

        VkGroup vkGroup = (VkGroup) o;

        return id.equals(vkGroup.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
