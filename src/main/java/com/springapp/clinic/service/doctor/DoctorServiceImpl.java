package com.springapp.clinic.service.doctor;

import com.springapp.clinic.mapper.DoctorMapper;
import com.springapp.clinic.model.dto.DoctorDto;
import com.springapp.clinic.model.entity.Doctor;
import com.springapp.clinic.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto){

        Doctor doctor = doctorMapper.doctorDtoToDoctor(doctorDto);

        Doctor savedDoctor = doctorRepository.save(doctor);

        return doctorMapper.doctorToDoctorDto(savedDoctor);
    }

    @Override
    public Optional<DoctorDto> getSingleDoctor(Long id){
        return doctorRepository.findById(id)
                .map(doctorMapper::doctorToDoctorDto);
    }

    @Override
    public Page<DoctorDto> getDoctorsPage(Pageable pageable){
        return doctorRepository.findAll(pageable)
                .map(doctorMapper::doctorToDoctorDto);
    }

    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto) {
        Doctor doctor = doctorMapper.doctorDtoToDoctor(doctorDto);
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMapper.doctorToDoctorDto(updatedDoctor);
    }

    @Override
    public boolean deleteDoctor(Long id){
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if(doctor.isPresent()) {
            doctorRepository.delete(doctor.get());
            return true;
        }
        return false;
    }
}
