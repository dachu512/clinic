package com.springapp.clinic.service.visit;

import com.springapp.clinic.mapper.VisitMapper;
import com.springapp.clinic.model.dto.VisitDto;
import com.springapp.clinic.model.entity.Patient;
import com.springapp.clinic.model.entity.Visit;
import com.springapp.clinic.repository.PatientRepository;
import com.springapp.clinic.repository.VisitRepository;
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

    @Override
    public VisitDto createVisit(Long patientId, VisitDto visitDto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Visit visit = visitMapper.visitDtoToVisit(visitDto);
        visit.setPatient(patient);
        Visit savedVisit = visitRepository.save(visit);
        return visitMapper.visitToVisitDto(savedVisit, patient);
    }

    @Override
    public VisitDto updateVisit(Long patientId, VisitDto visitDto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Visit visit = visitMapper.visitDtoToVisit(visitDto);
        visit.setPatient(patient);
        Visit savedVisit = visitRepository.save(visit);
        return visitMapper.visitToVisitDto(savedVisit, patient);
    }

    @Override
    public Page<VisitDto> getVisitsByPatient(Long patientId, Pageable pageable) {
        return visitRepository.findAllByPatientId(patientId, pageable)
                .map(visit -> visitMapper.visitToVisitDto(visit, visit.getPatient()));
    }

    @Override
    public Optional<VisitDto> getSingleVisitById(Long patientId, Long visitId) {
        return visitRepository.findById(visitId)
                .map(visit -> visitMapper.visitToVisitDto(visit, visit.getPatient()));
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
