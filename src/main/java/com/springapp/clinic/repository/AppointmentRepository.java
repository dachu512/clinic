package com.springapp.clinic.repository;

import com.springapp.clinic.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByPatientIdIn(List<Long> ids);
}
