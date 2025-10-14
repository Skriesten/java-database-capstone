package com.project.back_end.controllers;


import com.project.back_end.models.Appointment;
import com.project.back_end.services.AppointmentService;
import com.project.back_end.services.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.LongToDoubleFunction;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UtilityService utilityService;


    @GetMapping("(/{date}/{patientName}/{token})")
    public ResponseEntity<Map<String, Object>> getAppointments(@PathVariable LocalDate date,
                                 @PathVariable String patientName, @PathVariable String token){
        Map<String,Object> map = new HashMap<>();
        boolean isValid = utilityService.validateToken("doctor", token).hasBody();
        if(!isValid){
            map.put("message", "Token invalid");
            return (ResponseEntity<Map<String, Object>>) map;
        }
        else {
            LocalDate startDate = date.minusDays(1);
            LocalDate endDate = date.plusDays(1);
           map = appointmentService.getAppointments(patientName,startDate, endDate, token );
           return ResponseEntity.ok(map);
        }
    }

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, String>> bookAppointment(Appointment appointment, @PathVariable String token){
        boolean isValid = utilityService.validateToken("patient", token).hasBody();
        int apptStatus = utilityService.validateAppointment(appointment);
        // return 1;  appointment time is valid
        // return -1;  // the doctor does not exist
        // return 0;   // the time is unavailable

        Map<String,String> map = new HashMap<>();
        if(!isValid){
            map.put("message", "Patient is invalid");
            return (ResponseEntity<Map<String, String>>) map;
            }
            else if(apptStatus == -1) {  // the doctor does not exist
                map.put("message", "Appointment is invalid, doctor not valid");
                return (ResponseEntity<Map<String, String>>) map;
            }
            else if(apptStatus == 0){ // the time is unavailable
                map.put("message", "Appointment time is not available");
                return (ResponseEntity<Map<String, String>>) map;
            }
            else if(apptStatus == 1) {  //appointment time is valid
                appointmentService.bookAppointment(appointment);
                map.put("message", "Appointment time is available, appointment has been made.");
                map.put("message", "HTTP Status 201 Created");
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @PutMapping("/{token}")
    public ResponseEntity<Map<String, String>> updateAppointment(Appointment appointment, @PathVariable String token){
        Map<String,String> map = new HashMap<>();
        boolean isValid = utilityService.validateToken("patient", token).hasBody();
        if(!isValid){
            map.put("message", "Patient is invalid, appointment not updated");
            return (ResponseEntity<Map<String, String>>) map;
        }
        else{
            appointmentService.updateAppointment(appointment);
            map.put("message", "Appointment has been updated");
            map.put("message", "HTTP Status 201 Created");
            return (ResponseEntity<Map<String, String>>) map;
        }
    }

    @DeleteMapping("/{id}/{token}")
    public ResponseEntity<Map<String, String>> cancelAppointment(@PathVariable Long id, @PathVariable String token){
        Appointment appointment = new Appointment();
        appointment.setId(id);
        Map<String,String> map = new HashMap<>();
        boolean isValid = utilityService.validateToken("patient", token).hasBody();
        if(!isValid){
            map.put("message", "Patient is invalid, appointment not cancelled");
            return (ResponseEntity<Map<String, String>>) map;
        }
        else{
            appointmentService.cancelAppointment(appointment);
            map.put("message", "Appointment has been cancelled");
            map.put("message", "HTTP Status 200 Deleted");
            return (ResponseEntity<Map<String, String>>) map;
        }
    }

}

// 1. Set Up the Controller Class:
//    - Annotate the class with `@RestController` to define it as a REST API controller.
//    - Use `@RequestMapping("/appointments")` to set a base path for all appointment-related endpoints.
//    - This centralizes all routes that deal with booking, updating, retrieving, and canceling appointments.


// 2. Autowire Dependencies:
//    - Inject `AppointmentService` for handling the business logic specific to appointments.
//    - Inject the general `Service` class, which provides shared functionality like token validation and appointment checks.


// 3. Define the `getAppointments` Method:
//    - Handles HTTP GET requests to fetch appointments based on date and patient name.
//    - Takes the appointment date, patient name, and token as path variables.
//    - First validates the token for role `"doctor"` using the `Service`.
//    - If the token is valid, returns appointments for the given patient on the specified date.
//    - If the token is invalid or expired, responds with the appropriate message and status code.


// 4. Define the `bookAppointment` Method:
//    - Handles HTTP POST requests to create a new appointment.
//    - Accepts a validated `Appointment` object in the request body and a token as a path variable.
//    - Validates the token for the `"patient"` role.
//    - Uses service logic to validate the appointment data (e.g., check for doctor availability and time conflicts).
//    - Returns success if booked, or appropriate error messages if the doctor ID is invalid or the slot is already taken.


// 5. Define the `updateAppointment` Method:
//    - Handles HTTP PUT requests to modify an existing appointment.
//    - Accepts a validated `Appointment` object and a token as input.
//    - Validates the token for `"patient"` role.
//    - Delegates the update logic to the `AppointmentService`.
//    - Returns an appropriate success or failure response based on the update result.


// 6. Define the `cancelAppointment` Method:
//    - Handles HTTP DELETE requests to cancel a specific appointment.
//    - Accepts the appointment ID and a token as path variables.
//    - Validates the token for `"patient"` role to ensure the user is authorized to cancel the appointment.
//    - Calls `AppointmentService` to handle the cancellation process and returns the result.
