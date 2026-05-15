package com.project.medicalsystem.service;
import com.project.medicalsystem.model.Schedule;
import com.project.medicalsystem.util.FileUtil;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ScheduleService implements CrudService<Schedule> {
    private final String FILE = "schedules.txt";
    @Override
    public String generateId() {
        int next = getAll().size() + 1;
        String year = String.valueOf(Year.now().getValue());
        return String.format("SCH-%s-%03d", year, next);
    }
    @Override
    public void add(Schedule s) {
        FileUtil.writeToFile(FILE, s.toFileString());
    }
    @Override
    public List<Schedule> getAll() {
        List<Schedule> list = new ArrayList<>();
        for (String line : FileUtil.readFile(FILE)) {
            if (line == null || line.trim().isEmpty()) continue;
            Schedule s = Schedule.fromString(line);
            if (s != null) list.add(s);
        }
        return list;
    }
    @Override
    public void delete(String id) {
        List<String> updated = new ArrayList<>();
        for (String line : FileUtil.readFile(FILE)) {
            Schedule s = Schedule.fromString(line);
            if (s == null) continue;
            if (!s.getId().equals(id)) updated.add(s.toFileString());
        }
        FileUtil.overwriteFile(FILE, updated);
    }

    public String generateScheduleId() { return generateId(); }

    public Schedule findById(String id) {
        for (Schedule s : getAll()) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    public boolean bookSlot(String scheduleId) {
        List<String> updated = new ArrayList<>();
        boolean booked = false;
        for (String line : FileUtil.readFile(FILE)) {
            Schedule s = Schedule.fromString(line);
            if (s == null) continue;
            if (s.getId().equals(scheduleId) && s.isAvailable()) {
                s.setBookedPatients(s.getBookedPatients() + 1);
                booked = true;
            }
            updated.add(s.toFileString());
        }
        FileUtil.overwriteFile(FILE, updated);
        return booked;
    }
    public void update(String id, String date, String startTime,
                       String endTime, int maxPatients) {
        List<String> updated = new ArrayList<>();
        for (String line : FileUtil.readFile(FILE)) {
            Schedule s = Schedule.fromString(line);
            if (s == null) continue;
            if (s.getId().equals(id)) {
                s.setDate(date);
                s.setStartTime(startTime);
                s.setEndTime(endTime);
                s.setMaxPatients(maxPatients);
            }
            updated.add(s.toFileString());
        }
        FileUtil.overwriteFile(FILE, updated);
    }
}
