package com.project.medicalsystem.model;

public class Doctor extends BaseEntity {

    private String name;
    private String specialty;

    public Doctor(String id, String name, String specialty) {
        super(id);
        this.name = name;
        this.specialty = specialty;
    }


    public String getName()      { return name; }
    public String getSpecialty() { return specialty; }

    public void setName(String name)           { this.name = name; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }



    @Override
    public String toFileString() {
        return id + "," + name + "," + specialty;
    }

    @Override
    public String getDisplayInfo() {
        return "Name: " + name + " | Specialty: " + specialty;
    }


    public static Doctor fromString(String data) {
        String[] d = data.split(",");
        return new Doctor(d[0], d[1], d[2]);
    }
}
