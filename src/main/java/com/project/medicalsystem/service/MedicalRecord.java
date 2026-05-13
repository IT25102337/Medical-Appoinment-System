package com.project.medicalsystem.model;

public class MedicalRecord {

    private String id;
    private String patientId;
    private String doctorId;
    private String appointmentId;
    private String diagnosis;
    private String prescription;
    private String notes;
    private String date;

    public MedicalRecord(String id,
                         String patientId,
                         String doctorId,
                         String appointmentId,
                         String diagnosis,
                         String prescription,
                         String notes,
                         String date) {

        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
        this.date = date;
    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public String getAppointmentId() { return appointmentId; }
    public String getDiagnosis() { return diagnosis; }
    public String getPrescription() { return prescription; }
    public String getNotes() { return notes; }
    public String getDate() { return date; }

    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public void setPrescription(String prescription) { this.prescription = prescription; }
    public void setNotes(String notes) { this.notes = notes; }

    public String toFileString() {
        return id + "," + patientId + "," + doctorId + "," +
                appointmentId + "," + diagnosis + "," +
                prescription + "," + notes + "," + date;
    }

    public static MedicalRecord fromString(String line) {

        try {

            String[] r = line.split(",");

            if (r.length < 8) return null;

            return new MedicalRecord(
                    r[0], r[1], r[2], r[3],
                    r[4], r[5], r[6], r[7]
            );

        } catch (Exception e) {
            return null;
        }
    }
}