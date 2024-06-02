package com.springapp.clinic.controller;

import com.springapp.clinic.controller.dto.PatientDto;
import com.springapp.clinic.controller.dto.PatientNameDto;
import com.springapp.clinic.controller.mapper.PatientDtoMapper;
import com.springapp.clinic.model.Patient;
import com.springapp.clinic.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.springapp.clinic.controller.mapper.PatientNameDtoMapper.mapPatientToPatientNameDto;

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/api/v2/patients")
    public List<PatientDto> getPatients(@RequestParam(required = false) int page) {
        int pageNumber = page >= 0 ? page : 0;
        return PatientDtoMapper.mapToPatientDtos(patientService.getPatients(pageNumber));
    }

    @GetMapping("/api/v2/patients/appointments")
    public List<Patient> getPatientsWithAppointments(@RequestParam(required = false) int page) {
        int pageNumber = page >= 0 ? page : 0;
        return patientService.getPatientsWithAppointments(pageNumber);
    }

    @GetMapping("/api/v2/patients/name")
    public List<PatientNameDto> getPatientsNames(@RequestParam(required = false) int page) {
        int pageNumber = page >= 0 ? page : 0;
        return mapPatientToPatientNameDto(patientService.getPatients(pageNumber));
    }



    @GetMapping("/api/v2/patients/{id}")
    public Patient getSinglePatient(@PathVariable Long id){
        return patientService.getSinglePatient(id);
    }
}
