package com.timsmeet.dto;



public class Organization {

    private Long id;
    private Long lastModificationId;
    private String name;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {
        private final Organization organization = new Organization();

        public Organization build() {
            return organization;
        }

        public Builder id(Long id) {
            organization.setId(id);
            return this;
        }

        public Builder lastModificationId(Long lastModificationId) {
            organization.setLastModificationId(lastModificationId);
            return this;
        }
        
        public Builder name(String name) {
            organization.setName(name);
            return this;
        }
    }
}
