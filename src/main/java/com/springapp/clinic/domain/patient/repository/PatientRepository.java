package com.springapp.clinic.domain.patient.repository;

import com.springapp.clinic.domain.patient.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAll(Pageable pageable);
}
