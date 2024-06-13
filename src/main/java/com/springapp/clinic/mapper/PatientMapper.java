package com.springapp.clinic.mapper;

import com.springapp.clinic.model.dto.PatientDto;
import com.springapp.clinic.model.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientDtoToPatient(PatientDto patientDto);

    PatientDto patientToPatientDto(Patient patient);

}
