package com.project.medicalsystem.service;

import com.project.medicalsystem.model.Patient;
import com.project.medicalsystem.util.FileUtil;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private final String FILE = "patients.txt";


    public String generatePatientId() {

        List<Patient> patients = getAllPatients();

        int nextNumber = patients.size() + 1;

        String year = String.valueOf(Year.now().getValue());

        return String.format("PAT-%s-%03d", year, nextNumber);
    }


    public void addPatient(Patient p) {
        FileUtil.writeToFile(FILE, p.toFileString());
    }


    public List<Patient> getAllPatients() {

        List<Patient> list = new ArrayList<>();

        for (String line : FileUtil.readFile(FILE)) {

            if (line != null && !line.trim().isEmpty()) {
                list.add(Patient.fromString(line));
            }
        }

        return list;
    }


    public void updatePatient(String id, String newName, String newPhone) {

        List<String> updated = new ArrayList<>();

        for (String line : FileUtil.readFile(FILE)) {

            Patient p = Patient.fromString(line);

            if (p.getId().equals(id)) {
                p.setName(newName);
                p.setPhone(newPhone);
            }

            updated.add(p.toFileString());
        }

        FileUtil.overwriteFile(FILE, updated);
    }


    public void deletePatient(String id) {

        List<String> updated = new ArrayList<>();

        for (String line : FileUtil.readFile(FILE)) {

            Patient p = Patient.fromString(line);

            if (!p.getId().equals(id)) {
                updated.add(p.toFileString());
            }
        }

        FileUtil.overwriteFile(FILE, updated);
    }
}