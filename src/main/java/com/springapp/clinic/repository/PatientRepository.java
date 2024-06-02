package com.springapp.clinic.repository;

import com.springapp.clinic.model.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("Select p From Patient p")
    List<Patient> findAllPatients(Pageable page);
}
