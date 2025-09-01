
// Import overlay function
import {showBookingOverlay} from '../loggedPatient.js';

// Import deleteDoctor function
import {deleteDoctor} from '../services/doctorServices.js';

// Import fetch patent details function
import {getPatientData} from "../services/patientServices.js";

//function createDoctorCard(){
export function createDoctorCard(doctor) {
    // 1. Create the main container for the doctor card
    const cardContainer = document.createElement('div');
    cardContainer.classList.add('doctor-card');

    // 2. Retrieve the current user role from localStorage
    const role = localStorage.getItem('role');

    // 3. Create a div to hold doctor information
    const infoDiv = document.createElement('div');
    infoDiv.classList.add('doctor-info');

    // 4. Create and set the doctor’s name
    const name = document.createElement('h3');
    name.textContent = doctor.name;

    // 5. Create and set the doctor's specialization
    const specialization = document.createElement('p');
    specialization.textContent = `Specialization: ${doctor.specialty}`;

    // 6. Create and set the doctor's email
    const email = document.createElement('p');
    email.textContent = `Email: ${doctor.email}`;

    // 7. Create and list available appointment times
    const appointments = document.createElement('div');
    appointments.classList.add('appointment-times');
    const appointmentsTitle = document.createElement('h4');
    appointmentsTitle.textContent = 'Available Appointments:';
    appointments.appendChild(appointmentsTitle);

    const appointmentsList = document.createElement('ul');
    if (doctor.appointments && doctor.appointments.length > 0) {
        doctor.appointments.forEach(time => {
            const timeItem = document.createElement('li');
            timeItem.textContent = time;
            appointmentsList.appendChild(timeItem);
        });
    } else {
        const noAppointmentsItem = document.createElement('li');
        noAppointmentsItem.textContent = 'No appointments available.';
        appointmentsList.appendChild(noAppointmentsItem);
    }
    appointments.appendChild(appointmentsList);

    // 8. Append all info elements to the doctor info container
    infoDiv.appendChild(name);
    infoDiv.appendChild(specialization);
    infoDiv.appendChild(email);
    infoDiv.appendChild(appointments);

    // 9. Create a container for card action buttons
    const actionsContainer = document.createElement('div');
    actionsContainer.classList.add('card-actions');

    // Conditionally add buttons based on user role
    if (role === 'patient') {
        const bookButton = document.createElement('button');
        bookButton.textContent = 'Book Appointment';
        bookButton.classList.add('btn', 'btn-primary');
        bookButton.addEventListener('click', () => {
            if(role === 'admin'){
                const deleteBtn = document.createElement('button');
                deleteBtn.textContent = 'Delete';
                // Show a button that login is required, but alert first
              } else if(role ==='patient'){
                const bookNow = document.createElement('button');
                bookNow.textContent = 'Book Now';
                bookNow.addEventListener('click', () => {
                    alert('Patient needs to log in first.');  // alert patient to log in.
                });
                // Once patient is logged in
            } else if(role === 'loggedPatient'){
                const bookNow = document.createElement('button');
                bookNow.textContent = 'Book Now';
                bookNow.addEventListener('click', () => {
                    const token = localStorage.getItem('token');
                    const patientData = getPatientData(token);
                    showBookingOverlay(e, doctor,patientData);
                });
            }
            // Add booking logic here
            alert(`Booking an appointment with Dr. ${doctor.name}...`);
        });
        actionsContainer.appendChild(bookButton);
    }
    // Append info and actions containers to the main card container
    cardContainer.appendChild(infoContainer);
    cardContainer.appendChild(actionsContainer);
    return cardContainer;
}  // ************  End of createDoctorCard function  *********

// Logged-in Patient
// Assuming user is logged in as a 'patient'
localStorage.setItem('userRole', 'patient');
const newDoctorCard = createDoctorCard(doctorData);

// Append the card to a container in your HTML, e.g., a div with id="doctor-list"
document.getElementById('doctor-list').appendChild(newDoctorCard);

/*
Import the overlay function for booking appointments from loggedPatient.js
  Import the deleteDoctor API function to remove doctors (admin role) from doctorServices.js
  Import function to fetch patient details (used during booking) from patientServices.js

  Function to create and return a DOM element for a single doctor card
    Create the main container for the doctor card
    Retrieve the current user role from localStorage
    Create a div to hold doctor information --
    Create and set the doctor’s name
    Create and set the doctor's specialization
    Create and set the doctor's email
    Create and list available appointment times
    Append all info elements to the doctor info container
    Create a container for card action buttons

    === ADMIN ROLE ACTIONS ===
      Create a delete button
      Add click handler for delete button
     Get the admin token from localStorage
        Call API to delete the doctor
        Show result and remove card if successful
      Add delete button to actions container
   
    === PATIENT (NOT LOGGED-IN) ROLE ACTIONS ===
      Create a book now button
      Alert patient to log in before booking
      Add button to actions container
  
    === LOGGED-IN PATIENT ROLE ACTIONS === 
      Create a book now button
      Handle booking logic for logged-in patient   
        Redirect if token not available
        Fetch patient data with token
        Show booking overlay UI with doctor and patient info
      Add button to actions container
   
  Append doctor info and action buttons to the car
  Return the complete doctor card element
*/
