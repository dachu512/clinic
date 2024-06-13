package com.springapp.clinic.mapper;

import com.springapp.clinic.model.dto.PatientDto;
import com.springapp.clinic.model.dto.VisitDto;
import com.springapp.clinic.model.entity.Patient;
import com.springapp.clinic.model.entity.Visit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class VisitMapper {

    @Autowired
    PatientMapper patientMapper;

    public abstract Visit visitDtoToVisit(VisitDto visitDto);

    @Mapping(source = "visit.id", target = "id")
    @Mapping(source = "patient", target = "patientDto", qualifiedByName = "getPatientDto")
    public abstract VisitDto visitToVisitDto(Visit visit, Patient patient);

    @Named("getPatientDto")
    protected PatientDto getPatientDto(Patient patient) {

        return patientMapper.patientToPatientDto(patient);
    }
}
