package com.springapp.clinic.domain.visit.repository;

import com.springapp.clinic.domain.visit.entity.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long>, JpaSpecificationExecutor<Visit> {

    Page<Visit> findAll(Pageable pageable);

    Page<Visit> findAllByPatientId(Long id, Pageable pageable);

    Optional<Visit> findById(Long visitId);

}
