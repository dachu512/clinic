package com.springapp.clinic.domain.visit.service;

import com.springapp.clinic.domain.visit.mapper.VisitMapper;
import com.springapp.clinic.domain.visit.entity.VisitDto;
import com.springapp.clinic.domain.patient.entity.Patient;
import com.springapp.clinic.domain.doctor.entity.Doctor;
import com.springapp.clinic.domain.visit.entity.Visit;
import com.springapp.clinic.domain.doctor.repository.DoctorRepository;
import com.springapp.clinic.domain.patient.repository.PatientRepository;
import com.springapp.clinic.domain.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitMapper visitMapper;
    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public VisitDto createVisit(Long patientId, VisitDto visitDto, Long doctorId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found")); //TODO: nie zwracamy RuntimeException - code smell

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Visit visit = visitMapper.visitDtoToVisit(visitDto);
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        Visit savedVisit = visitRepository.save(visit);
        return visitMapper.visitToVisitDto(savedVisit, patient, doctor);
    }

    @Override
    public VisitDto updateVisit(Long patientId, VisitDto visitDto, Long doctorId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Visit visit = visitMapper.visitDtoToVisit(visitDto);
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        Visit savedVisit = visitRepository.save(visit);
        return visitMapper.visitToVisitDto(savedVisit, patient, doctor);
    }

    @Override
    public Page<VisitDto> getVisitsByPatient(Long patientId, Pageable pageable) {
        return visitRepository.findAllByPatientId(patientId, pageable)
                .map(visit -> visitMapper.visitToVisitDto(visit, visit.getPatient(), visit.getDoctor()));
    }

    @Override
    public Optional<VisitDto> getSingleVisitById(Long patientId, Long visitId) {
        return visitRepository.findById(visitId)
                .map(visit -> visitMapper.visitToVisitDto(visit, visit.getPatient(), visit.getDoctor()));
    }

    @Override
    public boolean deleteVisit(Long visitId) {
        Optional<Visit> visit = visitRepository.findById(visitId);
        if (visit.isPresent()) {
            visitRepository.delete(visit.get());
            return true;
        }
        return false;
    }
}
