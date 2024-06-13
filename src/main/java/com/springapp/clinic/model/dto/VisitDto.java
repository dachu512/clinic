package com.springapp.clinic.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class VisitDto {


    private Long id;

    @NotBlank(message = "Please provide a date")
    private LocalDate appointmentDate;

    @NotBlank(message = "Please provide a reason")
    private String reason;

    private PatientDto patientDto;
}
