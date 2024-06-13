package com.springapp.clinic.controller;

import com.springapp.clinic.exceptions.NotFoundException;
import com.springapp.clinic.model.dto.VisitDto;
import com.springapp.clinic.service.visit.VisitService;
import com.springapp.clinic.utils.PageableUtil;
import com.springapp.clinic.utils.VersionNumber;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(VersionNumber.VERSION_V1 + "patients")
@RequiredArgsConstructor
@Validated
@Slf4j
public class PatientVisitController {

    private final VisitService visitService;


    @GetMapping("/{patientId}/visits")
    public Page<VisitDto> getVisitsByPatient(@PathVariable Long patientId,
                                             @RequestParam(required = false) Integer page,
                                             @RequestParam(required = false) Integer size,
                                             @RequestParam(required = false) List<String> sort) {
        Pageable pageable = PageableUtil.buildPageable(page, size, sort);
        return visitService.getVisitsByPatient(patientId, pageable);
    }

    @GetMapping("/{patientId}/visits/{visitId}")
    public VisitDto getSingleVisitById(@PathVariable Long patientId, @PathVariable Long visitId) {
        log.info("Get visit by id: {}", visitId);
        return visitService.getSingleVisitById(patientId, visitId)
                .orElseThrow(() -> new NotFoundException("Not found entity by id: " + visitId));
    }

    @PostMapping("/{patientId}/visits")
    public VisitDto createVisit(@Valid @RequestBody VisitDto visitDto, @PathVariable Long patientId) {
        log.info("Add visit {}", visitDto);
        return visitService.createVisit(patientId, visitDto);
    }

    @PutMapping("/{patientId}/visits/{visitId}")
    public VisitDto updateVisit(@RequestBody VisitDto newVisitDto, @PathVariable Long patientId, @PathVariable Long visitId) {
        log.info("Update visit by id {}", visitId);
        return visitService.getSingleVisitById(patientId, visitId)
                .map(visitDto -> {
                    if(newVisitDto.getAppointmentDate() != null){
                        visitDto.setAppointmentDate(newVisitDto.getAppointmentDate());
                    }
                    if(newVisitDto.getReason() !=null){
                        visitDto.setReason(newVisitDto.getReason());
                    }
                    if(newVisitDto.getPatientDto() != null){
                        visitDto.setPatientDto(newVisitDto.getPatientDto());
                    }
                    return visitService.updateVisit(patientId, visitDto);
                }).orElseThrow(() -> new NotFoundException("Not found entity by id: " + visitId));
    }

    @DeleteMapping("/{patientId}/visits/{visitId}")
    public ResponseEntity<String> deleteVisit(@PathVariable Long visitId) {
        log.info("Deleting visit with id {}", visitId);
        boolean isDeleted = visitService.deleteVisit(visitId);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visit not found with id " + visitId);
        }
        String responseMessage = "Visit with id " + visitId + " has been deleted successfully.";
        return ResponseEntity.ok(responseMessage);
    }

}