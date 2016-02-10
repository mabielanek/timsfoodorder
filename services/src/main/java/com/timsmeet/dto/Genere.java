package com.timsmeet.dto;


public class Genere {

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

    public final static class Builder {
        private final Genere genere = new Genere();

        public Builder(String name) {
            genere.setName(name);
        }

        public Genere build() {
            return genere;
        }

        public Builder id(Long id) {
            genere.setId(id);
            return this;
        }

        public Builder lastModificationId(Long lastModificationId) {
            genere.setLastModificationId(lastModificationId);
            return this;
        }

        public Builder name(String name) {
            genere.setName(name);
            return this;
        }
    }
}
