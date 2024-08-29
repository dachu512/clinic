package com.springapp.clinic.domain.visit.mapper;

import com.springapp.clinic.domain.doctor.mapper.DoctorMapper;
import com.springapp.clinic.domain.patient.mapper.PatientMapper;
import com.springapp.clinic.domain.doctor.entity.DoctorDto;
import com.springapp.clinic.domain.patient.entity.PatientDto;
import com.springapp.clinic.domain.visit.entity.VisitDto;
import com.springapp.clinic.domain.doctor.entity.Doctor;
import com.springapp.clinic.domain.patient.entity.Patient;
import com.springapp.clinic.domain.visit.entity.Visit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class VisitMapper {

    @Autowired
    protected PatientMapper patientMapper;

    @Autowired
    protected DoctorMapper doctorMapper;

    public abstract Visit visitDtoToVisit(VisitDto visitDto);

    @Mapping(source = "visit.id", target = "id")
    @Mapping(source = "patient", target = "patientDto", qualifiedByName = "getPatientDto")
    @Mapping(source = "doctor", target = "doctorDto", qualifiedByName = "getDoctorDto")
    public abstract VisitDto visitToVisitDto(Visit visit, Patient patient, Doctor doctor);

    @Named("getPatientDto")
    protected PatientDto getPatientDto(Patient patient) {
        return patientMapper.patientToPatientDto(patient);
    }

    @Named("getDoctorDto")
    protected DoctorDto getDoctorDto(Doctor doctor) {
        return doctorMapper.doctorToDoctorDto(doctor);
    }
}
