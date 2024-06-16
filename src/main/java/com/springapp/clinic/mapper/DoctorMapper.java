package com.springapp.clinic.mapper;

import com.springapp.clinic.model.dto.DoctorDto;
import com.springapp.clinic.model.entity.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor doctorDtoToDoctor(DoctorDto doctorDto);

    DoctorDto doctorToDoctorDto(Doctor doctor);

}
