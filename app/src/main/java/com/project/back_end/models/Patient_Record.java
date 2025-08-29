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


    // ********* GETTERS AND SETTERS  **********************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDate_of_visit() {
        return date_of_visit;
    }

    public void setDate_of_visit(LocalDateTime date_of_visit) {
        this.date_of_visit = date_of_visit;
    }

    public String getVisit_summary() {
        return visit_summary;
    }

    public void setVisit_summary(String visit_summary) {
        this.visit_summary = visit_summary;
    }
}
