package com.project.medicalsystem.model;

public class Patient extends BaseEntity {

    private String name;
    private String phone;

    public Patient(String id, String name, String phone) {
        super(id);
        this.name = name;
        this.phone = phone;
    }


    public String getName()  { return name; }
    public String getPhone() { return phone; }

    public void setName(String name)   { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }


    @Override
    public String toFileString() {
        return id + "," + name + "," + phone;
    }

    @Override
    public String getDisplayInfo() {
        return "Name: " + name + " | Phone: " + phone;
    }


    public static Patient fromString(String data) {
        String[] p = data.split(",");
        return new Patient(p[0], p[1], p[2]);
    }
}
