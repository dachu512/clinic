package com.springapp.clinic.domain.patient.mapper;

import com.springapp.clinic.domain.patient.entity.PatientDto;
import com.springapp.clinic.domain.patient.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientDtoToPatient(PatientDto patientDto);

    PatientDto patientToPatientDto(Patient patient);

}
