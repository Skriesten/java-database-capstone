package com.project.back_end.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Appointment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "doctor_id")
       @JsonManagedReference
        private  Doctor doctor;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "patient_id")
        @JsonManagedReference
        private  Patient patient;

        private LocalDateTime appointment_time;

        @NotNull(message = "Status is required")
        private int status;

        @NotNull(message = "Reason for visit is required")
        private String condition;

        // *** METHODS **********************************8
        @Transient
        public LocalDateTime getEndTime(LocalDateTime appointmentTime) {
                // End Time is estimated to be one hour after initial time
                    java.time.LocalDateTime endTime =  appointmentTime.plusHours(1);
                    return endTime;
        }

        @Transient
        public LocalDate getAppointmentDate() {
        LocalDate apptDate = appointment_time.toLocalDate();
            return apptDate;
        }

        @Transient
        public LocalTime getAppointmentTimeOnly(LocalDateTime appointmentTime) {
            LocalTime apptTime = appointmentTime.toLocalTime();
            return apptTime;
        }

    // Parameterized constructor
    public Appointment(Long id, Doctor doctor, Patient patient, LocalDateTime appointment_time, int status) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.appointment_time = appointment_time;
        this.status = status;
    }

    // No Args Constructor
    public Appointment() {}

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getAppointmentTime() {
        return appointment_time;
    }

    public void setAppointmentTime(LocalDateTime appointment_time) {
        this.appointment_time = appointment_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason_for_visit() {
        return condition;
    }

    public void setReason_for_visit(String reason_for_visit) {
        this.condition = reason_for_visit;
    }

} // *********** END OF CLASS *******************************

// ************** INSTRUCTIONS ***************************
// @Entity annotation:
//    - Marks the class as a JPA entity, meaning it represents a table in the database.
//    - Required for persistence frameworks (e.g., Hibernate) to map the class to a database table.

// 1. 'id' field:
//    - Type: private Long
//    - Description:
//      - Represents the unique identifier for each appointment.
//      - The @Id annotation marks it as the primary key.
//      - The @GeneratedValue(strategy = GenerationType.IDENTITY) annotation auto-generates the ID value when a new record is inserted into the database.

// 2. 'doctor' field:
//    - Type: private Doctor
//    - Description:
//      - Represents the doctor assigned to this appointment.
//      - The @ManyToOne annotation defines the relationship, indicating many appointments can be linked to one doctor.
//      - The @NotNull annotation ensures that an appointment must be associated with a doctor when created.

// 3. 'patient' field:
//    - Type: private Patient
//    - Description:
//      - Represents the patient assigned to this appointment.
//      - The @ManyToOne annotation defines the relationship, indicating many appointments can be linked to one patient.
//      - The @NotNull annotation ensures that an appointment must be associated with a patient when created.

// 4. 'appointmentTime' field:
//    - Type: private LocalDateTime
//    - Description:
//      - Represents the date and time when the appointment is scheduled to occur.
//      - The @Future annotation ensures that the appointment time is always in the future when the appointment is created.
//      - It uses LocalDateTime, which includes both the date and time for the appointment.

// 5. 'status' field:
//    - Type: private int
//    - Description:
//      - Represents the current status of the appointment. It is an integer where:
//        - 0 means the appointment is scheduled.
//        - 1 means the appointment has been completed.
//      - The @NotNull annotation ensures that the status field is not null.

// 6. 'getEndTime' method:
//    - Type: private LocalDateTime
//    - Description:
//      - This method is a transient field (not persisted in the database).
//      - It calculates the end time of the appointment by adding one hour to the start time (appointmentTime).
//      - It is used to get an estimated appointment end time for display purposes.

// 7. 'getAppointmentDate' method:
//    - Type: private LocalDate
//    - Description:
//      - This method extracts only the date part from the appointmentTime field.
//      - It returns a LocalDate object representing just the date (without the time) of the scheduled appointment.

// 8. 'getAppointmentTimeOnly' method:
//    - Type: private LocalTime
//    - Description:
//      - This method extracts only the time part from the appointmentTime field.
//      - It returns a LocalTime object representing just the time (without the date) of the scheduled appointment.

// 9. Constructor(s):
//    - A no-argument constructor is implicitly provided by JPA for entity creation.
//    - A parameterized constructor can be added as needed to initialize fields.

// 10. Getters and Setters:
//    - Standard getter and setter methods are provided for accessing and modifying the fields: id, doctor, patient, appointmentTime, status, etc.