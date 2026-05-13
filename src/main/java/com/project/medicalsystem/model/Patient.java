package com.project.medicalsystem.model;

public class Patient {

    private String id;
    private String name;
    private String phone;

    public Patient(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toFileString() {
        return id + "," + name + "," + phone;
    }

    public static Patient fromString(String data) {
        String[] p = data.split(",");
        return new Patient(p[0], p[1], p[2]);
    }
}