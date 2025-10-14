package com.project.back_end.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name must be provided, cannot be more than 100 characters.")
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    private String user_name;

    @NotNull(message = "Email must be provided.")
    @Email
    private String email;

    @NotNull(message = "Password must be greater than 5 characters.")
    @Size(min = 6)
    private String password;

    @NotNull(message = "Phone number must be provided.  Supply only numbers, no dashes or parenthesis")
    @Pattern(regexp = "^[0-9]{10}$") // Alternate @Pattern(regexp = "\\d{10}")
    private String phone;

    @NotNull(message = "Address must be provided")
    @Size(max = 255)
    private String address;

    @NotNull(message = "Role must be filled in.")
    private String role;

    @NotNull(message = "Birth date must be filled in")
    @Past
    private LocalDate date_of_birth;

    @NotNull(message = "Registration date must be filled in.")
    @PastOrPresent
    private LocalDate date_registered;

    @NotNull(message = "Gender, MALE or FEMAILE must be filled in.")
    private String gender;

    @NotNull
    @Size(min = 3, max = 100)
    private String emergency_contact;

    @NotNull
    private Boolean active;

    @OneToMany(mappedBy = "patient")
    private Collection<Appointment> appointment;

//   This was creating an error due to no matching patient field
//   the corrected code is below
//    @OneToMany(mappedBy = "patient")
//    private List<Patient>  patient_id;

    @OneToMany(mappedBy = "primaryPatient")
    private List<Patient> dependents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_patient_id")
    private Patient primaryPatient;

    @OneToMany(mappedBy = "patient")
    private Collection<Patient_Record>  patientRecords;


    // ****  GETTERS AND SETTERS **************************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(Collection<Appointment> appointment) {
        this.appointment = appointment;
    }

//   Old text that was part of what was creating an error
//    public List<Patient> getPatient_id() {
//        return patient_id;
//    }
//
//    public void setPatient_id(List<Patient> patient_id) {
//        this.patient_id = patient_id;
//    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public LocalDate getDate_registered() {
        return date_registered;
    }

    public void setDate_registered(LocalDate date_registered) {
        this.date_registered = date_registered;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmergency_contact() {
        return emergency_contact;
    }

    public void setEmergency_contact(String emergency_contact) {
        this.emergency_contact = emergency_contact;
    }

    public List<Patient> getDependents() {
        return dependents;
    }

    public void setDependents(List<Patient> dependents) {
        this.dependents = dependents;
    }

    public Patient getPrimaryPatient() {
        return primaryPatient;
    }

    public void setPrimaryPatient(Patient primaryPatient) {
        this.primaryPatient = primaryPatient;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Collection<Patient_Record> getPatientRecords() {
        return patientRecords;
    }

    public void setPatientRecords(Collection<Patient_Record> patientRecords) {
        this.patientRecords = patientRecords;
    }
}  // ********** END OF CLASS  ******************************

//  **********  INSTRUCTIONS  *****************************
// @Entity annotation:
//    - Marks the class as a JPA entity, meaning it represents a table in the database.
//    - Required for persistence frameworks (e.g., Hibernate) to map the class to a database table.

// 1. 'id' field:
//    - Type: private Long
//    - Description:
//      - Represents the unique identifier for each patient.
//      - The @Id annotation marks it as the primary key.
//      - The @GeneratedValue(strategy = GenerationType.IDENTITY) annotation auto-generates the ID value when a new record is inserted into the database.

// 2. 'name' field:
//    - Type: private String
//    - Description:
//      - Represents the patient's full name.
//      - The @NotNull annotation ensures that the patient's name is required.
//      - The @Size(min = 3, max = 100) annotation ensures that the name length is between 3 and 100 characters.
//      - Provides validation for correct input and user experience.

// 3. 'email' field:
//    - Type: private String
//    - Description:
//      - Represents the patient's email address.
//      - The @NotNull annotation ensures that an email address must be provided.
//      - The @Email annotation validates that the email address follows a valid email format (e.g., patient@example.com).

// 4. 'password' field:
//    - Type: private String
//    - Description:
//      - Represents the patient's password for login authentication.
//      - The @NotNull annotation ensures that a password must be provided.
//      - The @Size(min = 6) annotation ensures that the password must be at least 6 characters long.

// 5. 'phone' field:
//    - Type: private String
//    - Description:
//      - Represents the patient's phone number.
//      - The @NotNull annotation ensures that a phone number must be provided.
//      - The @Pattern(regexp = "^[0-9]{10}$") annotation validates that the phone number must be exactly 10 digits long.

// 6. 'address' field:
//    - Type: private String
//    - Description:
//      - Represents the patient's address.
//      - The @NotNull annotation ensures that the address must be provided.
//      - The @Size(max = 255) annotation ensures that the address does not exceed 255 characters in length, providing validation for the address input.

// 7. Getters and Setters:
//    - Standard getter and setter methods are provided for all fields: id, name, email, password, phone, and address.
//    - These methods allow access and modification of the fields of the Patient class.
