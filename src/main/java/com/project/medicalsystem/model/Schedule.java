package com.project.medicalsystem.model;

public class Schedule extends BaseEntity {

    private String doctorId;
    private String date;
    private String startTime;
    private String endTime;
    private int maxPatients;
    private int bookedPatients;

    public Schedule(String id,
                    String doctorId,
                    String date,
                    String startTime,
                    String endTime,
                    int maxPatients,
                    int bookedPatients) {
        super(id);
        this.doctorId       = doctorId;
        this.date           = date;
        this.startTime      = startTime;
        this.endTime        = endTime;
        this.maxPatients    = maxPatients;
        this.bookedPatients = bookedPatients;
    }

    public String getDoctorId()      { return doctorId; }
    public String getDate()          { return date; }
    public String getStartTime()     { return startTime; }
    public String getEndTime()       { return endTime; }
    public int    getMaxPatients()   { return maxPatients; }
    public int    getBookedPatients(){ return bookedPatients; }


    public void setDate(String date)           { this.date = date; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public void setEndTime(String endTime)     { this.endTime = endTime; }
    public void setMaxPatients(int max)        { this.maxPatients = max; }
    public void setBookedPatients(int booked)  { this.bookedPatients = booked; }

    public boolean isAvailable() {
        return bookedPatients < maxPatients;
    }



    @Override
    public String toFileString() {
        return id + "," + doctorId + "," + date + "," +
               startTime + "," + endTime + "," +
               maxPatients + "," + bookedPatients;
    }

    @Override
    public String getDisplayInfo() {
        return "Doctor: " + doctorId +
               " | Date: " + date +
               " | Time: " + startTime + "-" + endTime +
               " | Slots: " + bookedPatients + "/" + maxPatients;
    }


    public static Schedule fromString(String line) {
        try {
            String[] s = line.split(",");
            if (s.length < 7) return null;
            return new Schedule(s[0], s[1], s[2], s[3], s[4],
                    Integer.parseInt(s[5]), Integer.parseInt(s[6]));
        } catch (Exception e) {
            return null;
        }
    }
}
