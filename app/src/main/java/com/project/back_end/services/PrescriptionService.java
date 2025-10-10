package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.models.Prescription;
import com.project.back_end.repo.PrescriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrescriptionService {
    private PrescriptionRepository prescriptionRepository;

    //Saves a prescription to the database
    //Returns a response with a message indicating the result of the save operation
    public ResponseEntity<Map<String, String>> savePrescription(PrescriptionRepository prescriptionRepository) {
        Map<String, String> response = null;
        try {
            response = new HashMap<>();
            List<Prescription> prescriptions = prescriptionRepository.findAll();
            if (!prescriptions.isEmpty()) {
                response.put("400 BAD REQUEST", "Prescription not saved");
                return ResponseEntity.badRequest().body(response);
            }
            prescriptions.add(prescriptionRepository.save(new Prescription()));
            response.put("201 CREATED", "Prescription saved");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status","error");
            response.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //Retrieves the prescription associated with a specific appointment ID
    //Returns a response containing the prescription details or an error message
    public ResponseEntity<Map<String, Object>> getPrescription(Long appointmentId) {
        Map<String, Object> responseBody = new HashMap<>();
        try {
            List<Prescription> prescriptionList = prescriptionRepository.findByAppointmentId(appointmentId);
            if (prescriptionList.isEmpty()) {
                responseBody.put("Status", "Error");
                return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
            }
                responseBody.put("200 OK ", prescriptionList);
                return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseBody.put("status", "error");
            responseBody.put("500 Internal Server Error", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

 // 1. **Add @Service Annotation**:
//    - The `@Service` annotation marks this class as a Spring service component, allowing Spring's container to manage it.
//    - This class contains the business logic related to managing prescriptions in the healthcare system.
//    - Instruction: Ensure the `@Service` annotation is applied to mark this class as a Spring-managed service.

// 2. **Constructor Injection for Dependencies**:
//    - The `PrescriptionService` class depends on the `PrescriptionRepository` to interact with the database.
//    - It is injected through the constructor, ensuring proper dependency management and enabling testing.
//    - Instruction: Constructor injection is a good practice, ensuring that all necessary dependencies are available at the time of service initialization.

// 3. **savePrescription Method**:
//    - This method saves a new prescription to the database.
//    - Before saving, it checks if a prescription already exists for the same appointment (using the appointment ID).
//    - If a prescription exists, it returns a `400 Bad Request` with a message stating the prescription already exists.
//    - If no prescription exists, it saves the new prescription and returns a `201 Created` status with a success message.
//    - Instruction: Handle errors by providing appropriate status codes and messages, ensuring that multiple prescriptions for the same appointment are not saved.

// 4. **getPrescription Method**:
//    - Retrieves a prescription associated with a specific appointment based on the `appointmentId`.
//    - If a prescription is found, it returns it within a map wrapped in a `200 OK` status.
//    - If there is an error while fetching the prescription, it logs the error and returns a `500 Internal Server Error` status with an error message.
//    - Instruction: Ensure that this method handles edge cases, such as no prescriptions found for the given appointment, by returning meaningful responses.

// 5. **Exception Handling and Error Responses**:
//    - Both methods (`savePrescription` and `getPrescription`) contain try-catch blocks to handle exceptions that may occur during database interaction.
//    - If an error occurs, the method logs the error and returns an HTTP `500 Internal Server Error` response with a corresponding error message.
//    - Instruction: Ensure that all potential exceptions are handled properly, and meaningful responses are returned to the client.


}
