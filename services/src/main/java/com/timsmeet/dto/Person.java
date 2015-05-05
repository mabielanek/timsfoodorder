package com.timsmeet.dto;

import com.timsmeet.dto.entity.BaseEntity;
import com.timsmeet.dto.entity.EntityState;

public class Person extends BaseEntity {
    private Long id;
    private Long lastModificationId;
    private String login;
    private String password;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public final static class Builder {
        private final Person orderSubItem = new Person();

        public Builder(EntityState entityState, String login) {
            orderSubItem.getEntityAspect().setEntityState(entityState);
            orderSubItem.setLogin(login);
        }

        public Person build() {
            return orderSubItem;
        }

        public Builder id(Long id) {
            orderSubItem.setId(id);
            return this;
        }

        public Builder lastModificationId(Long lastModificationId) {
            orderSubItem.setLastModificationId(lastModificationId);
            return this;
        }

        public Builder login(String login) {
            orderSubItem.setLogin(login);
            return this;
        }

        public Builder password(String password) {
            orderSubItem.setPassword(password);
            return this;
        }
    }
}
