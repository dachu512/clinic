package com.springapp.clinic.domain.visit.entity;


import com.springapp.clinic.enums.Status;
import com.springapp.clinic.domain.doctor.entity.DoctorDto;
import com.springapp.clinic.domain.patient.entity.PatientDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class VisitDto {


    private Long id;

    @NotNull(message = "Please provide a date")
    private LocalDate appointmentDate;

    @NotBlank(message = "Please provide a reason")
    private String reason;

    @NotNull(message = "Please provide a status")
    @Enumerated(EnumType.STRING)
    private Status status;

    private PatientDto patientDto;

    private DoctorDto doctorDto;
}
