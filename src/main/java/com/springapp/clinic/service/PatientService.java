package com.springapp.clinic.service;

import com.springapp.clinic.model.Patient;
import com.springapp.clinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }

    public Patient getSinglePatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow();
    }
}
