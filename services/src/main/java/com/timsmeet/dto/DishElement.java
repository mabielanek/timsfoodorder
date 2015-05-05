package com.timsmeet.dto;

import com.timsmeet.dto.entity.BaseEntity;
import com.timsmeet.dto.entity.EntityState;
import com.timsmeet.persistance.enums.ActivityStatus;

public class DishElement extends BaseEntity {

    private Long id;
    private Long lastModificationId;
    private ActivityStatus status;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLastModificationId() {
        return lastModificationId;
    }

    public void setLastModificationId(Long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public final static class Builder {
        private final DishElement dishElement = new DishElement();

        public Builder(EntityState entityState, ActivityStatus status) {
            dishElement.getEntityAspect().setEntityState(entityState);
            dishElement.setStatus(status);
        }

        public DishElement build() {
            return dishElement;
        }

        public Builder id(Long id) {
            dishElement.setId(id);
            return this;
        }

        public Builder lastModificationId(Long lastModificationId) {
            dishElement.setLastModificationId(lastModificationId);
            return this;
        }

        public Builder status(ActivityStatus status) {
            dishElement.setStatus(status);
            return this;
        }

        public Builder name(String name) {
            dishElement.setName(name);
            return this;
        }

        public Builder description(String description) {
            dishElement.setDescription(description);
            return this;
        }
    }
}
