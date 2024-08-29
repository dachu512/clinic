package com.springapp.clinic.domain.doctor.mapper;

import com.springapp.clinic.domain.doctor.entity.DoctorDto;
import com.springapp.clinic.domain.doctor.entity.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor doctorDtoToDoctor(DoctorDto doctorDto);

    DoctorDto doctorToDoctorDto(Doctor doctor);

}
