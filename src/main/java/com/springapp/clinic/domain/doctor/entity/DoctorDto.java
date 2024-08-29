package com.springapp.clinic.domain.doctor.entity;


import com.springapp.clinic.enums.Specialization;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorDto {

    private Long id;

    @NotBlank(message = "Please provide a first name")
    private String firstName;

    @NotBlank(message = "Please provide a last name")
    private String lastName;

    @NotNull(message = "Please provide a specialization")
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    private String phone;

    @Email
    private String email;
}
