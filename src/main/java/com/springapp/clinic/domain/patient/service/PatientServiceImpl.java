package com.springapp.clinic.domain.patient.service;

import com.springapp.clinic.domain.patient.mapper.PatientMapper;
import com.springapp.clinic.domain.patient.entity.PatientDto;
import com.springapp.clinic.domain.patient.entity.Patient;
import com.springapp.clinic.domain.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;

    @Override
    public PatientDto createPatient(PatientDto patientDto) {

        Patient patient = patientMapper.patientDtoToPatient(patientDto);

        Patient savedPatient = patientRepository.save(patient);

        return patientMapper.patientToPatientDto(savedPatient);
    }

    @Override
    public Optional<PatientDto> getSinglePatient(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::patientToPatientDto);
    }

    @Override
    public Page<PatientDto> getPatientsPage(Pageable pageable) {
        return patientRepository.findAll(pageable)
                .map(patientMapper::patientToPatientDto);
    }

    @Override
    public boolean deletePatient(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.delete(patient.get());
            return true;
        }
        return false;
    }
}
