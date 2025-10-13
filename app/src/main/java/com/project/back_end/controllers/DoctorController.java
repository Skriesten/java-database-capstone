package com.project.back_end.controllers;

import com.project.back_end.DTO.Login;
import com.project.back_end.models.Appointment;
import com.project.back_end.models.Doctor;
import com.project.back_end.repo.AppointmentRepository;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.path}" + "doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UtilityService utilityService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/availability/{role}/{doctorId}/{date}/{token}")
    public ResponseEntity<Map<String, String>> getDoctorAvailability(
          @PathVariable String role,
          @PathVariable Long doctorId,
          @PathVariable LocalDate date,
          @PathVariable String token) {
        Map<String, String> response = new HashMap<>();
        boolean isValid = utilityService.validateToken("Doctor", token).hasBody();
        Appointment appointment = new Appointment();
        int apptValid = utilityService.validateAppointment(appointment);
        if (!isValid) {
            response.put("message", "Doctor is not listed.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else if (apptValid == -1) {
            response.put("message", "Doctor is not listed.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else if (apptValid == 0) {
            response.put("message", "Time is not available.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else if (apptValid == 1) {
            doctorService.getDoctorAvailability(doctorId, date);
            response.put("message", "Time is available.");
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }
        else {
            return null;
        }

    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getDoctor(){
        Map<String, Object> response = new HashMap<>();
        List<Doctor> doctors = doctorService.getDoctors();
        response.put("doctors", doctors);
        return (ResponseEntity<Map<String, Object>>) ResponseEntity.status(HttpStatus.OK).body(response) ;
    }

    @PostMapping("/{token}")
    public ResponseEntity<Map<String , Object>> addDoctor(@PathVariable String token, @RequestBody Doctor doctor){
        Map<String , Object> response = new HashMap<>();
        boolean isValid =  utilityService.validateToken("Doctor", token).hasBody();
        if(!isValid){
            response.put("message", "Doctor already exists.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else if(isValid){
            doctorService.saveDoctor(doctor);
            response.put("message", "Doctor added to db.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else{
            response.put("message", "Some internal error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public Map<String, String> doctorLogin(@PathVariable Login login) throws Exception {
        Map<String,String> response = new HashMap<>();
        response = doctorService.validateDoctor(login).getBody();
        return response;
    }

    @PutMapping("/{token}")
    public Map<String, String> updateDoctor(@PathVariable String token, @RequestBody Doctor doctor){
        Map<String,String> response = new HashMap<>();
        boolean isValid = utilityService.validateToken("Admin", token).hasBody();
        if(!isValid) {
            response.put("message", "Doctor not found.");
            return response;
        }
        else if(isValid){
            doctorService.updateDoctor(doctor);
            response.put("message", "Doctor updated.");
            return response;
        }
        else {
            response.put("message", "Some internal error occurred.");
            return response;
        }
    }


    @DeleteMapping("/{id}/{token}")
    public ResponseEntity<Map<String, String>> deleteDoctor(@PathVariable Long doctorId, @PathVariable String token){
        Map<String,String> response = new HashMap<>();
        boolean isValid = utilityService.validateToken("Doctor", token).hasBody();
        if(!isValid) {
            response.put("message", "Doctor not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        else if(isValid){
            doctorService.deleteDoctor(doctorId);
            response.put("message", "Doctor deleted.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            response.put("message", "Some internal error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/filter/{name}/{time}/{speciality}")
    public Map<String, Object> filterDoctors(@PathVariable String doctorName, @PathVariable LocalDateTime time, @PathVariable String specialty){
        Map<String,Object> response = new HashMap<>();
        response = utilityService.filterDoctor(doctorName, specialty, String.valueOf(time));
        return response;
    }

    // return 1;  appointment time is valid
    // return -1;  // the doctor does not exist
    // return 0;   // the time is unavailable
//  ***** INSTRUCTIONS
// 1. Set Up the Controller Class:
//    - Annotate the class with `@RestController` to define it as a REST controller that serves JSON responses.
//    - Use `@RequestMapping("${api.path}doctor")` to prefix all endpoints with a configurable API path followed by "doctor".
//    - This class manages doctor-related functionalities such as registration, login, updates, and availability.

// 2. Autowire Dependencies:
//    - Inject `DoctorService` for handling the core logic related to doctors (e.g., CRUD operations, authentication).
//    - Inject the shared `Service` class for general-purpose features like token validation and filtering.

// 3. Define the `getDoctorAvailability` Method:
//    - Handles HTTP GET requests to check a specific doctor’s availability on a given date.
//    - Requires `user` type, `doctorId`, `date`, and `token` as path variables.
//    - First validates the token against the user type.
//    - If the token is invalid, returns an error response; otherwise, returns the availability status for the doctor.

// 4. Define the `getDoctor` Method:
//    - Handles HTTP GET requests to retrieve a list of all doctors.
//    - Returns the list within a response map under the key `"doctors"` with HTTP 200 OK status.

// 5. Define the `saveDoctor` Method:
//    - Handles HTTP POST requests to register a new doctor.
//    - Accepts a validated `Doctor` object in the request body and a token for authorization.
//    - Validates the token for the `"admin"` role before proceeding.
//    - If the doctor already exists, returns a conflict response; otherwise, adds the doctor and returns a success message.

// 6. Define the `doctorLogin` Method:
//    - Handles HTTP POST requests for doctor login.
//    - Accepts a validated `Login` DTO containing credentials.
//    - Delegates authentication to the `DoctorService` and returns login status and token information.

// 7. Define the `updateDoctor` Method:
//    - Handles HTTP PUT requests to update an existing doctor's information.
//    - Accepts a validated `Doctor` object and a token for authorization.
//    - Token must belong to an `"admin"`.
//    - If the doctor exists, updates the record and returns success; otherwise, returns not found or error messages.

// 8. Define the `deleteDoctor` Method:
//    - Handles HTTP DELETE requests to remove a doctor by ID.
//    - Requires both doctor ID and an admin token as path variables.
//    - If the doctor exists, deletes the record and returns a success message; otherwise, responds with a not found or error message.

// 9. Define the `filter` Method:
//    - Handles HTTP GET requests to filter doctors based on name, time, and specialty.
//    - Accepts `name`, `time`, and `speciality` as path variables.
//    - Calls the shared `Service` to perform filtering logic and returns matching doctors in the response.

}  //  END OF CONTROLLER CLASS
