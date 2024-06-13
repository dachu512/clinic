package com.springapp.clinic.service.patient;

import com.springapp.clinic.model.dto.PatientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PatientService {

    PatientDto createPatient(PatientDto patientDto);

    Optional<PatientDto> getSinglePatient(Long id);

    Page<PatientDto> getPatientsPage(Pageable pageable);

    boolean deletePatient(Long id);
}
