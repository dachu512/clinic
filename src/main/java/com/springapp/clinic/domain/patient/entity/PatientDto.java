package com.springapp.clinic.domain.patient.entity;

import com.springapp.clinic.enums.Gender;
import com.springapp.clinic.utils.EnumValidator;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PatientDto {

    private Long id;

    @NotBlank(message = "Please provide a first name")
    private String firstName;

    @NotBlank(message = "Please provide a last name")
    private String lastName;

    @NotNull(message = "Please provide a date of birth")
    @Past(message = "Date must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Please provide a gender")
    @Enumerated(EnumType.STRING)
    @EnumValidator(anyOf = {Gender.MALE, Gender.FEMALE, Gender.OTHER}, message = "Invalid gender")
    private Gender gender;

    private String phone;

    @Email
    private String email;

    private String address;

}
