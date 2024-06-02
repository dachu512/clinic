package com.springapp.clinic.service;

import com.springapp.clinic.model.Appointment;
import com.springapp.clinic.model.Patient;
import com.springapp.clinic.repository.AppointmentRepository;
import com.springapp.clinic.repository.PatientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private static final int PAGE_SIZE = 5;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientService(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Patient> getPatients(int page){
        return patientRepository.findAllPatients(PageRequest.of(page,PAGE_SIZE));
    }

    public Patient getSinglePatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow();
    }

    public List<Patient> getPatientsWithAppointments(int page) {
        List<Patient> allPatients = patientRepository.findAllPatients(PageRequest.of(page,PAGE_SIZE));
        List<Long> ids = allPatients.stream()
                .map(Patient::getId)
                .toList();
        List<Appointment> appoitments = appointmentRepository.findAllByPatientIdIn(ids);
        allPatients.forEach(patient -> patient.setAppointment(extractAppointments(appoitments, patient.getId())));
        return allPatients;
    }

    private List<Appointment> extractAppointments(List<Appointment> appoitments, Long id) {
        return appoitments.stream()
                .filter(appointment -> appointment.getPatientId() == id)
                .toList();
    }
}
