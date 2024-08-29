package com.springapp.clinic.domain.patient.service;

import com.springapp.clinic.domain.patient.entity.PatientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PatientService {

    PatientDto createPatient(PatientDto patientDto);

    Optional<PatientDto> getSinglePatient(Long id);

    Page<PatientDto> getPatientsPage(Pageable pageable);

    boolean deletePatient(Long id);
}
