package com.project.back_end.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Patient_Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonManagedReference
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonManagedReference
    private Doctor doctor;

    @NotNull
    private LocalDateTime date_of_visit;

    @NotNull
    @Size(min = 1, max = 2000)
    private String visit_summary;

}
