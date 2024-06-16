package com.springapp.clinic.controller;

import com.springapp.clinic.exceptions.NotFoundException;
import com.springapp.clinic.model.dto.DoctorDto;
import com.springapp.clinic.model.dto.PatientDto;
import com.springapp.clinic.repository.DoctorRepository;
import com.springapp.clinic.model.entity.Doctor;
import com.springapp.clinic.service.doctor.DoctorService;
import com.springapp.clinic.utils.PageableUtil;
import com.springapp.clinic.utils.VersionNumber;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(VersionNumber.VERSION_V1 + "doctors")
@RequiredArgsConstructor
@Validated
@Slf4j
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public Page<DoctorDto> getDoctors(@RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size,
                                      @RequestParam(required = false) List<String> sort) {
        Pageable pageable = PageableUtil.buildPageable(page, size, sort);
        return doctorService.getDoctorsPage(pageable);
    }

    @GetMapping("/{id}")
    public DoctorDto getSingleDoctor(@PathVariable Long id) {
        log.info("Get doctor by id: {}", id);
        return doctorService.getSingleDoctor(id)
                .orElseThrow(() -> new NotFoundException("Not found entity by id: " + id));
    }

    @PostMapping
    public DoctorDto createDoctor(@RequestBody @Valid DoctorDto doctorDto){
        log.info("Add doctor {}", doctorDto);
        return doctorService.createDoctor(doctorDto);
    }

    @PutMapping("/{id}")
    public DoctorDto updateDoctor(@RequestBody @Valid DoctorDto newDoctorDto, @PathVariable Long id){
        log.info("Update doctor by id {}", id);
        return doctorService.getSingleDoctor(id)
                .map(doctorDto -> {
                    if(newDoctorDto.getFirstName() != null){
                        doctorDto.setFirstName(newDoctorDto.getFirstName());
                    }
                    if(newDoctorDto.getLastName() != null){
                        doctorDto.setLastName(newDoctorDto.getLastName());
                    }
                    if(newDoctorDto.getSpecialization() != null){
                        doctorDto.setSpecialization(newDoctorDto.getSpecialization());
                    }
                    if(newDoctorDto.getPhone() != null){
                        doctorDto.setPhone(newDoctorDto.getPhone());
                    }
                    if(newDoctorDto.getEmail() != null){
                        doctorDto.setEmail(newDoctorDto.getEmail());
                    }
                    return doctorService.updateDoctor(doctorDto);
                }).orElseThrow(() -> new NotFoundException("Not found entity by id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        log.info("Deleting doctor with id {}", id);
        boolean isDeleted = doctorService.deleteDoctor(id);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found with id " + id);
        }
        String responseMessage = "Doctor with id " + id + " has been deleted successfully.";
        return ResponseEntity.ok(responseMessage);
    }


}