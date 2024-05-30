package com.springapp.clinic.controller;

import com.springapp.clinic.model.Patient;
import com.springapp.clinic.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/api/v2/patients")
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/api/v2/patients/{id}")
    public Patient getSinglePatient(@PathVariable Long id){
        return patientService.getSinglePatient(id);
    }
}
