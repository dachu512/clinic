package com.springapp.clinic.appointment;

import com.springapp.clinic.patient.Patient;
import com.springapp.clinic.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/api/patients/{patientId}/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointmentsByPatientId(@PathVariable(value = "patientId") Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }


    @PostMapping("/api/patients/{patientId}/appointments")
    public ResponseEntity<Appointment> createAppointment(@PathVariable(value = "patientId") Long patientId,
                                                         @RequestBody Appointment appointmentRequest) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Patient patient = patientOptional.get();
        appointmentRequest.setPatient(patient);
        Appointment savedAppointment = appointmentRepository.save(appointmentRequest);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }
}

