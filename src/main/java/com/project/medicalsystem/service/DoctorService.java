package com.project.medicalsystem.service;
import com.project.medicalsystem.model.Doctor;
import com.project.medicalsystem.util.FileUtil;
import org.springframework.stereotype.Service;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService implements CrudService<Doctor> {
    private final String FILE = "doctors.txt";
    @Override
    public String generateId() {
        int nextNumber = getAllDoctors().size() + 1;
        String year = String.valueOf(Year.now().getValue());
        return String.format("DOC-%s-%03d", year, nextNumber);
    }
    @Override
    public void add(Doctor d) {
        if (d == null) return;
        FileUtil.writeToFile(FILE, d.toFileString());
    }
    @Override
    public List<Doctor> getAll() {
        return getAllDoctors();
    }
    @Override
    public void delete(String id) {
        if (id == null || id.trim().isEmpty()) return;
        List<String> updated = new ArrayList<>();
        for (String line : FileUtil.readFile(FILE)) {
            if (line == null || line.trim().isEmpty()) continue;
            try {
                Doctor d = Doctor.fromString(line);
                if (!d.getId().trim().equals(id.trim())) {
                    updated.add(d.toFileString());
                }
            } catch (Exception e) {
                updated.add(line);
            }
        }
        FileUtil.overwriteFile(FILE, updated);
    }

    public String generateDoctorId() { return generateId(); }

    public void addDoctor(Doctor d)  { add(d); }

    public List<Doctor> getAllDoctors() {
        List<Doctor> list = new ArrayList<>();
        for (String line : FileUtil.readFile(FILE)) {
            if (line == null || line.trim().isEmpty()) continue;
            try { list.add(Doctor.fromString(line)); } catch (Exception ignored) {}
        }
        return list;
    }

    public void updateDoctor(String id, String name, String specialty) {
        if (id == null || id.trim().isEmpty()) return;
        List<String> updated = new ArrayList<>();
        for (String line : FileUtil.readFile(FILE)) {
            if (line == null || line.trim().isEmpty()) continue;
            try {
                Doctor d = Doctor.fromString(line);
                if (d.getId().trim().equals(id.trim())) {
                    if (name != null && !name.trim().isEmpty()) d.setName(name);
                    if (specialty != null && !specialty.trim().isEmpty()) d.setSpecialty(specialty);
                }
                updated.add(d.toFileString());
            } catch (Exception e) {
                updated.add(line);
            }
        }
        FileUtil.overwriteFile(FILE, updated);
    }

    public void deleteDoctor(String id) { delete(id); }
}
