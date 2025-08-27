package com.project.back_end.models;

import jakarta.persistence.*;

@Entity
public class DoctorAvailableTimes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private  String available_times;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getAvailable_times() {
        return available_times;
    }

    public void setAvailable_times(String available_times) {
        this.available_times = available_times;
    }
}
