package com.project.medicalsystem.model;

public abstract class BaseEntity {

    protected String id;

    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract String toFileString();


    public abstract String getDisplayInfo();


    public String getEntityType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return getEntityType() + "[" + id + "]: " + getDisplayInfo();
    }
}
