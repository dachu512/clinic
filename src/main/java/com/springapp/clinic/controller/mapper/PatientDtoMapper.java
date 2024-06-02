package com.springapp.clinic.controller.mapper;

import com.springapp.clinic.controller.dto.PatientDto;
import com.springapp.clinic.model.Patient;

import java.util.List;
import java.util.stream.Collectors;



public class PatientDtoMapper {

    private PatientDtoMapper() {
    }

    public static List<PatientDto> mapToPatientDtos(List<Patient> patients) {
        return patients.stream()
                .map(patient -> mapToPatientDto(patient))
                .collect(Collectors.toList());
    }

    private static PatientDto mapToPatientDto(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .phone(patient.getPhone())
                .email(patient.getEmail())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .address(patient.getAddress())
                .build();
    }
}
