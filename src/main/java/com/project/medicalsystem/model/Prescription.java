package com.project.medicalsystem.model;

public class Prescription extends BaseEntity {

    private String patientId;

    private String patientName;

    private String doctorId;

    private String specialty;

    private String medicine;

    private String dosage;

    private String usage;

    private String notes;
    public Prescription(String id,
                        String patientId,
                        String patientName,
                        String doctorId,
                        String specialty,
                        String medicine,
                        String dosage,
                        String usage,
                        String notes) {
        super(id);   
        this.patientId   = patientId;
        this.patientName = patientName;
        this.doctorId    = doctorId;
        this.specialty   = specialty;
        this.medicine    = medicine;
        this.dosage      = dosage;
        this.usage       = usage;
        this.notes       = notes;
    }

    public String getPatientId()   { return patientId; }

    public String getPatientName() { return patientName; }

    public String getDoctorId()    { return doctorId; }

    public String getSpecialty()   { return specialty; }

    public String getMedicine()    { return medicine; }

    public String getDosage()      { return dosage; }

    public String getUsage()       { return usage; }

    public String getNotes()       { return notes; }

    public void setMedicine(String medicine) { this.medicine = medicine; }

    public void setDosage(String dosage)     { this.dosage = dosage; }

    public void setUsage(String usage)       { this.usage = usage; }

    public void setNotes(String notes)       { this.notes = notes; }
    @Override
    public String toFileString() {
        return id + "," + patientId + "," + patientName + "," +
               doctorId + "," + specialty + "," +
               medicine + "," + dosage + "," + usage + "," + notes;
    }
    @Override
    public String getDisplayInfo() {
        return "Patient: " + patientName +
               " | Doctor: " + doctorId +
               " | Medicine: " + medicine +
               " | Dosage: " + dosage;
    }
    public static Prescription fromString(String line) {
        try {
            String[] p = line.split(",");
            if (p.length < 9) return null;
            return new Prescription(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8]);
        } catch (Exception e) {
            return null;
        }
    }
}
