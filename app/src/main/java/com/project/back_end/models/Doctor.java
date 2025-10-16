package com.project.back_end.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 100)
    @NotNull(message = "Doctor's name must be entered.")
    private String name;

    @NotNull(message = "User name is required.")
    @Size(min = 3, max = 50)
    private String userName;

    @NotNull(message = "Specialty/Profession is required")
    @Size(min = 3, max = 100)
    private String specialty;

    @NotNull(message = "Email is required")
    @Email
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "Phone number must be provided")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")// Alternate @Pattern(regexp = "\\d{10}")
    private String phone;

    @NotNull(message = "Role must be filled in.")
    private String role;

    @NotNull(message = "Date hired must be provided.")
    @PastOrPresent
    private LocalDate date_hired;

    private Boolean active;
    private Double salary;

    @NotNull(message = "Medical License number must be filled in.")
    private String medical_license_no;

    @NotNull(message = "Clinic address must be filled in.")
    @Size(min = 3, max = 200)
    private String clinic_address;

//   This is set up wrong, correct code to follow
//    @ElementCollection
//    private List<String> available_times;  //List of available time slots (Example: "09:00 -10:00")

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoctorAvailableTimes> availableTimes;

    @OneToMany(mappedBy = "doctor")
    private Collection<Appointment> appointment;

    @OneToMany(mappedBy = "doctor")
    private Collection<Patient_Record>  patientRecords;

    //  *********  GETTERS AND SETTERS  ********************

    public Collection<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(Collection<Appointment> appointment) {
        this.appointment = appointment;
    }

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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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

    public List<DoctorAvailableTimes> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<DoctorAvailableTimes> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getDate_hired() {
        return date_hired;
    }

    public void setDate_hired(LocalDate date_hired) {
        this.date_hired = date_hired;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getMedical_license_no() {
        return medical_license_no;
    }

    public void setMedical_license_no(String medical_license_no) {
        this.medical_license_no = medical_license_no;
    }

    public String getClinic_address() {
        return clinic_address;
    }

    public void setClinic_address(String clinic_address) {
        this.clinic_address = clinic_address;
    }

    public Collection<Patient_Record> getPatientRecords() {
        return patientRecords;
    }

    public void setPatientRecords(Collection<Patient_Record> patientRecords) {
        this.patientRecords = patientRecords;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}        //  ===========  END OF CLASS =========================

// ************* INSTRUCTIONS *************************
// @Entity annotation:
//    - Marks the class as a JPA entity, meaning it represents a table in the database.
//    - Required for persistence frameworks (e.g., Hibernate) to map the class to a database table.

// 1. 'id' field:
//    - Type: private Long
//    - Description:
//      - Represents the unique identifier for each doctor.
//      - The @Id annotation marks it as the primary key.
//      - The @GeneratedValue(strategy = GenerationType.IDENTITY) annotation auto-generates the ID value when a new record is inserted into the database.

// 2. 'name' field:
//    - Type: private String
//    - Description:
//      - Represents the doctor's name.
//      - The @NotNull annotation ensures that the doctor's name is required.
//      - The @Size(min = 3, max = 100) annotation ensures that the name length is between 3 and 100 characters.
//      - Provides validation for correct input and user experience.

// 3. 'specialty' field:
//    - Type: private String
//    - Description:
//      - Represents the medical specialty of the doctor.
//      - The @NotNull annotation ensures that a specialty must be provided.
//      - The @Size(min = 3, max = 50) annotation ensures that the specialty name is between 3 and 50 characters long.

// 4. 'email' field:
//    - Type: private String
//    - Description:
//      - Represents the doctor's email address.
//      - The @NotNull annotation ensures that an email address is required.
//      - The @Email annotation validates that the email address follows a valid email format (e.g., doctor@example.com).

// 5. 'password' field:
//    - Type: private String
//    - Description:
//      - Represents the doctor's password for login authentication.
//      - The @NotNull annotation ensures that a password must be provided.
//      - The @Size(min = 6) annotation ensures that the password must be at least 6 characters long.
//      - The @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) annotation ensures that the password is not serialized in the response (hidden from the frontend).

// 6. 'phone' field:
//    - Type: private String
//    - Description:
//      - Represents the doctor's phone number.
//      - The @NotNull annotation ensures that a phone number must be provided.
//      - The @Pattern(regexp = "^[0-9]{10}$") annotation validates that the phone number must be exactly 10 digits long.

// 7. 'availableTimes' field:
//    - Type: private List<String>
//    - Description:
//      - Represents the available times for the doctor in a list of time slots.
//      - Each time slot is represented as a string (e.g., "09:00-10:00", "10:00-11:00").
//      - The @ElementCollection annotation ensures that the list of time slots is stored as a separate collection in the database.

// 8. Getters and Setters:
//    - Standard getter and setter methods are provided for all fields: id, name, specialty, email, password, phone, and availableTimes.