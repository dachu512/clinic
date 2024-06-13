package com.springapp.clinic.service.visit;

import com.springapp.clinic.model.dto.VisitDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VisitService {

    VisitDto createVisit(Long patientId, VisitDto visitDto);

    VisitDto updateVisit(Long patientId, VisitDto visitDto);

    Page<VisitDto> getVisitsByPatient(Long patientId, Pageable pageable);

    Optional<VisitDto> getSingleVisitById(Long patientId, Long visitId);

    boolean deleteVisit(Long visitId);
}
