package com.project.back_end.DTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentDTO {
//    private Long id;
//    private Long doctorId;
//    private String doctorName;
//    private Long patientId;
//    private String patientName;
//    private String patientEmail;
//    private String patientPhone;
//    private String patientAddress;
//    private LocalDateTime appointmentTime;
//    private int status;
//    private LocalDateTime appointmentDate;//   - Extracted from the 'appointmentTime' field.
//    private LocalTime appointmentTimeOnly;  //  - Extracted from the 'appointmentTime' field.
//    private LocalDateTime endTime;  //   - Calculated by adding 1 hour to the 'appointmentTime' field.
//
//    //  ***  CONSTRUCTOR *************************************
//    public AppointmentDTO(Long id, Long doctorId, String doctorName, Long patientId, String patientName, String patientEmail,
//                          String patientPhone, String patientAddress, LocalDateTime appointmentTime, int status,
//                          LocalDateTime appointmentDate, LocalTime appointmentTimeOnly, LocalDateTime endTime) {
//                                    this.id = id;
//                                    this.doctorId = doctorId;
//                                    this.doctorName = doctorName;
//                                    this.patientId = patientId;
//                                    this.patientName = patientName;
//                                    this.patientEmail = patientEmail;
//                                    this.patientPhone = patientPhone;
//                                    this.patientAddress = patientAddress;
//                                    this.appointmentTime = appointmentTime;
//                                    this.status = status;
//                                    this.appointmentDate = appointmentDate;  // Calculate
//                                    this.appointmentTimeOnly = appointmentTimeOnly;  // based on appointmentTime field
//                                    this.endTime = endTime;  // based on appointmentTime field
//    }
//
////  ******  GETTERS AND SETTERS  **************************
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getPatientAddress() {
//        return patientAddress;
//    }
//
//    public void setPatientAddress(String patientAddress) {
//        this.patientAddress = patientAddress;
//    }
//
//    public String getPatientPhone() {
//        return patientPhone;
//    }
//
//    public void setPatientPhone(String patientPhone) {
//        this.patientPhone = patientPhone;
//    }
//
//    public String getPatientEmail() {
//        return patientEmail;
//    }
//
//    public void setPatientEmail(String patientEmail) {
//        this.patientEmail = patientEmail;
//    }
//
//    public String getPatientName() {
//        return patientName;
//    }
//
//    public void setPatientName(String patientName) {
//        this.patientName = patientName;
//    }
//
//    public Long getPatientId() {
//        return patientId;
//    }
//
//    public void setPatientId(Long patientId) {
//        this.patientId = patientId;
//    }
//
//    public String getDoctorName() {
//        return doctorName;
//    }
//
//    public void setDoctorName(String doctorName) {
//        this.doctorName = doctorName;
//    }
//
//    public Long getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(Long doctorId) {
//        this.doctorId = doctorId;
//    }
//
//
//    public LocalDateTime getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(LocalDateTime endTime) {
//        this.endTime = endTime;
//    }
//
//    public LocalDateTime getAppointmentTime() {
//        return appointmentTime;
//    }
//
//    public void setAppointmentTime(LocalDateTime appointmentTime) {
//        this.appointmentTime = appointmentTime;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public LocalDateTime getAppointmentDate() {
//        return appointmentDate;
//    }
//
//    public void setAppointmentDate(LocalDateTime appointmentDate) {
//        this.appointmentDate = appointmentDate;
//    }
//
//    public LocalTime getAppointmentTimeOnly() {
//        return appointmentTimeOnly;
//    }
//
//    public void setAppointmentTimeOnly(LocalTime appointmentTimeOnly) {
//        this.appointmentTimeOnly = appointmentTimeOnly;
//    }
}
// 1. 'id' field:
//    - Type: private Long
//    - Description:
//      - Represents the unique identifier for the appointment.
//      - This is the primary key for identifying the appointment in the system.

// 2. 'doctorId' field:
//    - Type: private Long
//    - Description:
//      - Represents the ID of the doctor associated with the appointment.
//      - This is a simplified field, capturing only the ID of the doctor (not the full Doctor object).

// 3. 'doctorName' field:
//    - Type: private String
//    - Description:
//      - Represents the name of the doctor associated with the appointment.
//      - This is a simplified field for displaying the doctor's name.

// 4. 'patientId' field:
//    - Type: private Long
//    - Description:
//      - Represents the ID of the patient associated with the appointment.
//      - This is a simplified field, capturing only the ID of the patient (not the full Patient object).

// 5. 'patientName' field:
//    - Type: private String
//    - Description:
//      - Represents the name of the patient associated with the appointment.
//      - This is a simplified field for displaying the patient's name.

// 6. 'patientEmail' field:
//    - Type: private String
//    - Description:
//      - Represents the email of the patient associated with the appointment.
//      - This is a simplified field for displaying the patient's email.

// 7. 'patientPhone' field:
//    - Type: private String
//    - Description:
//      - Represents the phone number of the patient associated with the appointment.
//      - This is a simplified field for displaying the patient's phone number.

// 8. 'patientAddress' field:
//    - Type: private String
//    - Description:
//      - Represents the address of the patient associated with the appointment.
//      - This is a simplified field for displaying the patient's address.

// 9. 'appointmentTime' field:
//    - Type: private LocalDateTime
//    - Description:
//      - Represents the scheduled date and time of the appointment.
//      - The time when the appointment is supposed to happen, stored as a LocalDateTime object.

// 10. 'status' field:
//    - Type: private int
//    - Description:
//      - Represents the status of the appointment.
//      - Status can indicate if the appointment is "Scheduled:0", "Completed:1",
//      or other statuses (e.g., "Canceled") as needed.

// 11. 'appointmentDate' field (Custom Getter):
//    - Type: private LocalDate
//    - Description:
//      - A derived field representing only the date part of the appointment (without the time).
//      - Extracted from the 'appointmentTime' field.

// 12. 'appointmentTimeOnly' field (Custom Getter):
//    - Type: private LocalTime
//    - Description:
//      - A derived field representing only the time part of the appointment (without the date).
//      - Extracted from the 'appointmentTime' field.

// 13. 'endTime' field (Custom Getter):
//    - Type: private LocalDateTime
//    - Description:
//      - A derived field representing the end time of the appointment.
//      - Calculated by adding 1 hour to the 'appointmentTime' field.

// 14. Constructor:
//    - The constructor accepts all the relevant fields for the AppointmentDTO,
//    including simplified fields for the doctor and patient (ID, name, etc.).
//    - It also calculates custom fields: 'appointmentDate', 'appointmentTimeOnly',
//    and 'endTime' based on the 'appointmentTime' field.

// 15. Getters:
//    - Standard getter methods are provided for all fields:
//    id, doctorId, doctorName, patientId, patientName, patientEmail, patientPhone,
//    patientAddress, appointmentTime, status, appointmentDate, appointmentTimeOnly, and endTime.
//    - These methods allow access to the values of the fields in the AppointmentDTO object.