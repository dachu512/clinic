package com.springapp.clinic.domain.doctor.service;

import com.springapp.clinic.domain.doctor.entity.DoctorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DoctorService {

    DoctorDto createDoctor(DoctorDto doctorDto);

    Optional<DoctorDto> getSingleDoctor(Long id);

    Page<DoctorDto> getDoctorsPage(Pageable pageable);

    DoctorDto updateDoctor(DoctorDto doctorDto);

    boolean deleteDoctor(Long id);
}
