package com.project.back_end.controllers;

import com.project.back_end.DTO.Login;
import com.project.back_end.models.Patient;
import com.project.back_end.services.PatientService;
import com.project.back_end.services.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private UtilityService utilityService;

    @GetMapping("/{token}")
    public ResponseEntity<Map<String, String>> getPatient(@PathVariable String token) {
        Map<String,String> response = new HashMap<>();
        boolean isValid = utilityService.validateToken("patient", token).hasBody();
        if(!isValid) {
            response.put("message", "Invalid Token");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else {
            response = (Map<String, String>) patientService.getPatientDetails(token);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> savePatient(@RequestBody Patient patient) {
        Map<String,Object> response = new HashMap<>();
        boolean isValid = utilityService.validatePatient(patient);
        if(!isValid) {
            response.put("message", "Patient with email, id or phone number already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else if(isValid) {
            patientService.createPatient(patient);
            response.put("message", "Patient created successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        else {
            response.put("message", "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Login login) throws Exception {
        Map<String,Object> response = new HashMap<>();
        response = (Map<String, Object>) utilityService.validatePatientLogin(login);
        if(response.isEmpty()){
            response.put("message", "Invalid Login");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else {
            response.put("message", "Patient Login successful");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }


    @GetMapping("/{id}/{token}")
    public ResponseEntity<Map<String, Object>> getPatientAppointments(@PathVariable Long id, @PathVariable String token) {
        Map<String,Object> response = new HashMap<>();
        boolean isValid = utilityService.validateToken("patient", token).hasBody();
        if(!isValid) {
            response.put("message", "Invalid Token");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else if(isValid) {
            patientService.getPatientAppointment(id, token);
            response.put("message", "Patient appointment successful");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            response.put("message", "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


//    @GetMapping("/filter/{condition}/{name}/{token}")
//    public ResponseEntity<Map<String, Object>> filterPatientAppointments(@PathVariable String condition, @PathVariable String name, @PathVariable String token) {
//        Map<String,Object> response = new HashMap<>();
//        boolean isValid = utilityService.validateToken("patient", token).hasBody();
//        if(!isValid) {
//            response.put("message", "Invalid Token");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//        else if(isValid) {
//            utilityService.filterPatient();
//        }
//    }



// 1. Set Up the Controller Class:
//    - Annotate the class with `@RestController` to define it as a REST API controller for patient-related operations.
//    - Use `@RequestMapping("/patient")` to prefix all endpoints with `/patient`, grouping all patient functionalities under a common route.


// 2. Autowire Dependencies:
//    - Inject `PatientService` to handle patient-specific logic such as creation, retrieval, and appointments.
//    - Inject the shared `Service` class for tasks like token validation and login authentication.


// 3. Define the `getPatient` Method:
//    - Handles HTTP GET requests to retrieve patient details using a token.
//    - Validates the token for the `"patient"` role using the shared service.
//    - If the token is valid, returns patient information; otherwise, returns an appropriate error message.


// 4. Define the `createPatient` Method:
//    - Handles HTTP POST requests for patient registration.
//    - Accepts a validated `Patient` object in the request body.
//    - First checks if the patient already exists using the shared service.
//    - If validation passes, attempts to create the patient and returns success or error messages based on the outcome.


// 5. Define the `login` Method:
//    - Handles HTTP POST requests for patient login.
//    - Accepts a `Login` DTO containing email/userName and password.
//    - Delegates authentication to the `validatePatientLogin` method in the shared service.
//    - Returns a response with a token or an error message depending on login success.


// 6. Define the `getPatientAppointment` Method:
//    - Handles HTTP GET requests to fetch appointment details for a specific patient.
//    - Requires the patient ID, token, and user role as path variables.
//    - Validates the token using the shared service.
//    - If valid, retrieves the patient's appointment data from `PatientService`; otherwise, returns a validation error.


// 7. Define the `filterPatientAppointment` Method:
//    - Handles HTTP GET requests to filter a patient's appointments based on specific conditions.
//    - Accepts filtering parameters: `condition`, `name`, and a token.
//    - Token must be valid for a `"patient"` role.
//    - If valid, delegates filtering logic to the shared service and returns the filtered result.



}


