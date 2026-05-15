package com.project.medicalsystem.service;
import com.project.medicalsystem.model.MedicalRecord;
import com.project.medicalsystem.util.FileUtil;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService implements CrudService<MedicalRecord> {
    private final String FILE = "medical_records.txt";
    @Override
    public String generateId() {
        int next = getAll().size() + 1;
        String year = String.valueOf(Year.now().getValue());
        return String.format("MR-%s-%03d", year, next);
    }
    @Override
    public void add(MedicalRecord r) {
        if (r.getId() == null || r.getId().isEmpty()) {
            r = new MedicalRecord(
                    generateId(),
                    r.getPatientId(), r.getDoctorId(), r.getAppointmentId(),
                    r.getDiagnosis(), r.getPrescription(), r.getNotes(), r.getDate()
            );
        }
        FileUtil.writeToFile(FILE, r.toFileString());
    }
    @Override
    public List<MedicalRecord> getAll() {
        List<MedicalRecord> list = new ArrayList<>();
        for (String line : FileUtil.readFile(FILE)) {
            if (line == null || line.trim().isEmpty()) continue;
            MedicalRecord r = MedicalRecord.fromString(line);
            if (r != null) list.add(r);
        }
        return list;
    }
    @Override
    public void delete(String id) {
        List<String> updated = new ArrayList<>();
        for (String line : FileUtil.readFile(FILE)) {
            MedicalRecord r = MedicalRecord.fromString(line);
            if (r == null) continue;
            if (!r.getId().equals(id)) updated.add(r.toFileString());
        }
        FileUtil.overwriteFile(FILE, updated);
    }

    public void update(String id, String diagnosis, String prescription, String notes) {
        List<String> updated = new ArrayList<>();
        for (String line : FileUtil.readFile(FILE)) {
            MedicalRecord r = MedicalRecord.fromString(line);
            if (r == null) continue;
            if (r.getId().equals(id)) {
                r.setDiagnosis(diagnosis);
                r.setPrescription(prescription);
                r.setNotes(notes);
            }
            updated.add(r.toFileString());
        }
        FileUtil.overwriteFile(FILE, updated);
    }
}
