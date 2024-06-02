package com.springapp.clinic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "appointmentDate")
    private LocalDateTime appointmentDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "patientId")
    private Long patientId;

}
