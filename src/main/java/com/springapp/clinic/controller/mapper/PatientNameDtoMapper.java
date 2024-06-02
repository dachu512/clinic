package com.springapp.clinic.controller.mapper;

import com.springapp.clinic.controller.dto.PatientNameDto;
import com.springapp.clinic.model.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class PatientNameDtoMapper {

    public static List<PatientNameDto> mapPatientToPatientNameDto(List<Patient> patients) {
        return patients.stream()
                .map(patient -> new PatientNameDto(patient.getId(), patient.getFirstName(), patient.getLastName()))
                .collect(Collectors.toList());
    }
}
