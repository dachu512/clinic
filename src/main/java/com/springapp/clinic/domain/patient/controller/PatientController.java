package com.springapp.clinic.domain.patient.controller;

import com.springapp.clinic.config.exceptions.NotFoundException;
import com.springapp.clinic.domain.patient.entity.PatientDto;
import com.springapp.clinic.domain.patient.service.PatientService;
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
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{id}")
    public PatientDto getSinglePatient(@PathVariable Long id) {
        log.info("Get patient by id: {}", id);
        return patientService.getSinglePatient(id)
                .orElseThrow(() -> new NotFoundException("Not found entity by id: " + id));
    }

    @GetMapping
    public Page<PatientDto> getPatients(@RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer size,
                                        @RequestParam(required = false) List<String> sort) {
        Pageable pageable = PageableUtil.buildPageable(page, size, sort);
        return patientService.getPatientsPage(pageable);
    }

    @PostMapping
    public PatientDto createPatient(@Valid @RequestBody PatientDto patientDto) {
        log.info("Add patients {}", patientDto);
        return patientService.createPatient(patientDto);
    }

    @PutMapping("/{id}")
    public PatientDto updatePatient(@RequestBody @Valid PatientDto newPatientDto, @PathVariable Long id) {
        log.info("Update patient by id {}", id);
        return patientService.getSinglePatient(id)
                .map(patientDto -> {
                    patientDto.setFirstName(newPatientDto.getFirstName());
                    patientDto.setLastName(newPatientDto.getLastName());
                    patientDto.setDateOfBirth(newPatientDto.getDateOfBirth());
                    patientDto.setGender(newPatientDto.getGender());
                    patientDto.setPhone(newPatientDto.getPhone());
                    patientDto.setEmail(newPatientDto.getEmail());
                    return patientService.createPatient(patientDto);
                }).orElseThrow(() -> new NotFoundException("Not found entity by id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        log.info("Deleting patient with id {}", id);
        boolean isDeleted = patientService.deletePatient(id);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found with id " + id);
        }
        String responseMessage = "Patient with id " + id + " has been deleted successfully.";
        return ResponseEntity.ok(responseMessage);
    }
}
