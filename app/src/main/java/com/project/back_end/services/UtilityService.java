package com.project.back_end.services;

import com.project.back_end.DTO.Login;
import com.project.back_end.models.Admin;
import com.project.back_end.models.Appointment;
import com.project.back_end.models.Patient;
import com.project.back_end.repo.AdminRepository;
import com.project.back_end.repo.AppointmentRepository;
import com.project.back_end.repo.DoctorRepository;
import com.project.back_end.repo.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

 // was having a conflict with the original file name of 'Service.java'
 // renamed it "UtilityService"
 @Service
 public class UtilityService {

    private final TokenService tokenService;
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
     private final AppointmentRepository appointmentRepository;

     // general construct
    public UtilityService(TokenService tokenService, AdminRepository adminRepository, DoctorRepository doctorRepository,
                          PatientRepository patientRepository, DoctorService doctorService, PatientService patientService, AppointmentRepository appointmentRepository) {
        this.tokenService = tokenService;
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentRepository = appointmentRepository;
    }

    public ResponseEntity<Map<String, String>> validateToken(String user, String token) {
      Admin admin = adminRepository.findByUsername(user);
      if(admin.getPassword().equals(token)) {
          return ResponseEntity.ok().build();
      }
      else {
          return ResponseEntity.badRequest().build();
      }
    }

    public ResponseEntity<Map<String, String>>  validateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
           if(admin.getPassword().equals(password)) {
               tokenService.generateToken(username);
               return ResponseEntity.ok().build();
            }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    public Map<String, Object>  filterDoctor(String name, String specialty, String time){
      Map<String, Object> filteredDoctors = doctorService.findDoctorByNameSpecialtyAndTime(name, specialty, time);
      Map<String, Object> response = new HashMap<>();
      response.put("doctors", filteredDoctors);
      return response;
    }

    public int validateAppointment(Appointment appointment){
        Long apptDoctorId = appointment.getDoctor().getId();
        Long doctorId = doctorRepository.getReferenceById(apptDoctorId).getId();
        LocalDate appointmentDate= appointment.getAppointmentDate();

        if(apptDoctorId.equals(doctorId)){
            if(appointmentDate.isEqual((ChronoLocalDate) doctorService.getDoctorAvailability(doctorId, appointmentDate))){
                return 1;
            }
        }
        if(!apptDoctorId.equals(doctorId)){
            return -1;
        }
        else {
            return 0;
        }
    }

    public boolean validatePatient(Patient patient){
        boolean valid = patientRepository.findByEmailOrPhone(patient.getEmail(), patient.getPhone()).getActive();
        return valid;
    }

    public ResponseEntity<Map<String, String>> validatePatientLogin(Login login){
        boolean valid = patientRepository.findByEmail(login.getEmail()).getActive();
        if(valid){
            tokenService.generateToken(login.getEmail());
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

//    public ResponseEntity<Map<String, Object>> filterPatient(String condition, String doctorName, String token){
//        boolean isPatient = tokenService.validateToken(token, "patient");
//        String patientCondition = condition;
//        String appDocotorName = doctorName;
//        String patientToken = token;
//        // look for only condition entered
//        if(!patientCondition.isBlank() && appDocotorName.isBlank() && !isPatient){
//            List<Appointment> appointmentList  = appointmentRepository.;
//            patientService.filterByCondition(condition,patientId);
//        }
//
//        patientRepository.getById();
//
//        patientService.filterByDoctor(doctorName);
//        patientService.filterByDoctorandCondition(doctorName, condition);
//        return ResponseEntity.ok().build();return ResponseEntity.ok().build();
  //  }
}  //*******  END OF CLASS  *******************************


/*   ************  INSTRUCTIONS  ***************************
 1. **@Service Annotation**
 The @Service annotation marks this class as a service component in Spring. 
 This allows Spring to automatically detect it through component scanning
 and manage its lifecycle, enabling it to be injected into controllers or other services
 using @Autowired or constructor injection.

 2. **Constructor Injection for Dependencies**
 The constructor injects all required dependencies (TokenService, Repositories, and other Services). 
 This approach promotes loose coupling, improves testability,
 and ensures that all required dependencies are provided at object creation time.

 3. **validateToken Method**
 This method checks if the provided JWT token is valid for a specific user. It uses the TokenService to perform the validation.
 If the token is invalid or expired, it returns a 401 Unauthorized response with an appropriate error message. This ensures security by preventing
 unauthorized access to protected resources.

 4. **validateAdmin Method**
 This method validates the login credentials for an admin user.
 - It first searches the admin repository using the provided username.
 - If an admin is found, it checks if the password matches.
 - If the password is correct, it generates and returns a JWT token (using the admin’s username) with a 200 OK status.
 - If the password is incorrect, it returns a 401 Unauthorized status with an error message.
 - If no admin is found, it also returns a 401 Unauthorized.
 - If any unexpected error occurs during the process, a 500 Internal Server Error response is returned.
 This method ensures that only valid admin users can access secured parts of the system.

 5. **filterDoctor Method**
 This method provides filtering functionality for doctors based on name, specialty, and available time slots.
 - It supports various combinations of the three filters.
 - If none of the filters are provided, it returns all available doctors.
 This flexible filtering mechanism allows the frontend or consumers of the API to search and narrow down doctors based on user criteria.

 6. **validateAppointment Method**
 This method validates if the requested appointment time for a doctor is available.
 - It first checks if the doctor exists in the repository.
 - Then, it retrieves the list of available time slots for the doctor on the specified date.
 - It compares the requested appointment time with the start times of these slots.
 - If a match is found, it returns 1 (valid appointment time).
 - If no matching time slot is found, it returns 0 (invalid).
 - If the doctor doesn’t exist, it returns -1.
 This logic prevents overlapping or invalid appointment bookings.

 7. **validatePatient Method**
 This method checks whether a patient with the same email or phone number already exists in the system.
 - If a match is found, it returns false (indicating the patient is not valid for new registration).
 - If no match is found, it returns true.
 This helps enforce uniqueness constraints on patient records and prevent duplicate entries.

 8. **validatePatientLogin Method**
 This method handles login validation for patient users.
 - It looks up the patient by email.
 - If found, it checks whether the provided password matches the stored one.
 - On successful validation, it generates a JWT token and returns it with a 200 OK status.
 - If the password is incorrect or the patient doesn't exist, it returns a 401 Unauthorized with a relevant error.
 - If an exception occurs, it returns a 500 Internal Server Error.
 This method ensures only legitimate patients can log in and access their data securely.

 9. **filterPatient Method**
 This method filters a patient's appointment history based on condition and doctor name.
 - It extracts the email from the JWT token to identify the patient.
 - Depending on which filters (condition, doctor name) are provided, it delegates the filtering logic to PatientService.
 - If no filters are provided, it retrieves all appointments for the patient.
 This flexible method supports patient-specific querying and enhances user experience on the client side.
*/