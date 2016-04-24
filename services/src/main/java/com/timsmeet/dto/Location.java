package com.timsmeet.dto;



public class Location {

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
        private final Location location = new Location();

        public Location build() {
            return location;
        }

        public Builder id(Long id) {
            location.setId(id);
            return this;
        }

        public Builder lastModificationId(Long lastModificationId) {
            location.setLastModificationId(lastModificationId);
            return this;
        }
        
        public Builder name(String name) {
            location.setName(name);
            return this;
        }
    }
}
