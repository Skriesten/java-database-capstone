package com.project.back_end.controllers;

import com.project.back_end.models.Prescription;
import com.project.back_end.mongorepo.PrescriptionRepository;
import com.project.back_end.services.AppointmentService;
import com.project.back_end.services.PrescriptionService;
import com.project.back_end.services.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${api.path}prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private UtilityService utilityService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, Object>> savePrescription(@RequestBody Prescription prescription, @PathVariable String token){
        Map<String, Object> response = new HashMap<>();
        boolean isValid = utilityService.validateToken("Doctor", token).hasBody();
        if(!isValid){
            response.put("error", "Invalid Token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        else {
            prescriptionService.savePrescription((PrescriptionRepository) prescription);
            response.put("message", prescription);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping("/{appointmentId}/{token}")
    public  ResponseEntity<Map<String, Object>> getPrescriptionByAppointmentId(@PathVariable Long appointmentId, @PathVariable String token){
        Map<String, Object> response = new HashMap<>();
        boolean isValid = utilityService.validateToken("Doctor", token).hasBody();
        if(!isValid){
            response.put("error", "Invalid Token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        else if (isValid && !prescriptionService.getPrescription(appointmentId).hasBody()) {
            response.put("error", "Prescription Not Found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        else {
           response.put("Valid ID", prescriptionService.getPrescription(appointmentId));
           return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
// 1. Set Up the Controller Class:
//    - Annotate the class with `@RestController` to define it as a REST API controller.
//    - Use `@RequestMapping("${api.path}prescription")` to set the base path for all
//    prescription-related endpoints.
//    - This controller manages creating and retrieving prescriptions tied to appointments.

// 2. Autowire Dependencies:
//    - Inject `PrescriptionService` to handle logic related to saving and fetching prescriptions.
//    - Inject the shared `Service` class for token validation and role-based access control.
//    - Inject `AppointmentService` to update appointment status after a prescription is issued.

// 3. Define the `savePrescription` Method:
//    - Handles HTTP POST requests to save a new prescription for a given appointment.
//    - Accepts a validated `Prescription` object in the request body and a doctor’s token as a
//    path variable.
//    - Validates the token for the `"doctor"` role.
//    - If the token is valid, updates the status of the corresponding appointment to reflect that a
//    prescription has been added.
//    - Delegates the saving logic to `PrescriptionService` and returns a response indicating
//    success or failure.


// 4. Define the `getPrescription` Method:
//    - Handles HTTP GET requests to retrieve a prescription by its associated appointment ID.
//    - Accepts the appointment ID and a doctor’s token as path variables.
//    - Validates the token for the `"doctor"` role using the shared service.
//    - If the token is valid, fetches the prescription using the `PrescriptionService`.
//    - Returns the prescription details or an appropriate error message if validation fails.


}
