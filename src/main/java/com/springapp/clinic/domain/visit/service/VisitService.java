package com.springapp.clinic.domain.visit.service;

import com.springapp.clinic.domain.visit.entity.VisitDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VisitService {

    VisitDto createVisit(Long patientId, VisitDto visitDto, Long doctorId);

    VisitDto updateVisit(Long patientId, VisitDto visitDto, Long doctorId);

    Page<VisitDto> getVisitsByPatient(Long patientId, Pageable pageable);

    Optional<VisitDto> getSingleVisitById(Long patientId, Long visitId);

    boolean deleteVisit(Long visitId);
}
