The Smart Clinic Management System will be an application that utilizes the
Spring Boot architecture for managing data and interacting with the database
using both MVC and REST controllers.
It will include dashboards used by both the Admin and Doctors by way of using
Thymeleaf templates.  Whereas the other modules will be using the REST APIs.
There will be two types of databases used for this application: MySQL and MongoDB.
MySQL will  manage the data for the patient, doctor, appointment and admin.
Its models will use the JPA entities.  MongoDB will manage the prescription data and will use document models.
The views will route through the controllers then into the service layer and then into the
repositories.

**## Steps showing dataflow and control**
1. The application will start off with two different Dashboards
2. One dashboard for  doctors and admin.
3. The second dashboard will be for Appointments, Patient Records and Patient access
4. Access to data for the first dashboard will be through the Thymeleaf Controllers
5. The Thymeleaf controllers will then call  the Service Layer.
6. The other modules for Appointments, Patient Records and Patient access will 
     use the REST controllers for data access.
7. These will also then call the Service Layer
8. From the Service Layer the data flow will access the repositories
     for both databases (MySQL and MongoDB).
9. The flow will then go to the Model Layer
10. Each database will have its own type of model data access.  For MySQL it 
      will use  the JPA entity model and for  MongoDB it will use the Document model.
11. The data collection for the MySQL model will be: Patient, Doctor, Appointment and Admin.
12. The data collection for the MongoDB model will be: Prescription.