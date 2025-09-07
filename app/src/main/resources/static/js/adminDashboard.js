import {openModal} from "./components/modals";
import {getDoctors} from "./services/doctorServices.js";
import {createDoctorCard} from "./components/doctorCard";
import {filterDoctors} from "./services/doctorServices.js";
import {saveDoctor} from "./services/doctorServices.js";



document.getElementById('addDoctor').
    addEventListener('click', (e) => { openModal('addDoctor');
});
window.onload = () => {loadDoctorCards()};

function loadDoctorCards(){
    const contentDiv = document.getElementById('content');
    contentDiv.innerHTML = ""
    const response = getDoctors();
    for (let i = 0; i < response.length; i++) {
        contentDiv.appendChild(createDoctorCard(response[i]));
    }
}

document.getElementById('searchBar').addEventListener("input", filterDoctorsOnChange);
document.getElementById("filterTime").addEventListener("change", filterDoctorsOnChange);
document.getElementById("filterSpecialty").addEventListener("change", filterDoctorsOnChange);

function filterDoctorsOnChange(e){
    const results = filterDoctors();
    if (!results.ok ) {
        alert("No doctors found.");
    }
}

function renderDoctorCards(doctors){
    const contentDiv = document.getElementById('content');
    contentDiv.innerHTML = ""
    for(let i = 0; i < doctors.length; i++){
        contentDiv.appendChild(createDoctorCard(doctors[i]));
    }
}

function adminAddDoctor(){
    document.getElementById('addDoctor').addEventListener('click', (e) => {
        openModal();
    });
    document.getElementById('modal-body').innerHTML +=
        '<p>Name: <p th:text =@{name} ></p><br></p>' +
        '<p>Email: <p th:text = @{email}></p><br>' +
        '<p>Phone: <p th:text =@{phone}></p><br></p>' +
        '<p>Role: <p th:text =@{role}></p><br></p>' +
        '<p>Specialty:<p th:text = @{specialty}></p><br></p>' +
        '<p> Date Hired:<p th:text = @{date_hired}></p><br></p>' +
        '<p>Active:<p th:text = @{active}></p><br></p>' +
        '<p>Salary:<p th:text = @{salary}></p><br></p>' +
        '<p>Medial License:<p th:text = @{medical_license_no}></p><br></p>' +
        '<p>Clinic Address:<p th:text = @{clinic_address}></p></p>'
}


/*  ***********  INSTRUCTIONS  *****************************
  This script handles the admin dashboard  functionality for managing doctors:
  - Loads all doctor cards
  - Filters doctors by name, time, or specialty
  - Adds a new doctor via modal form

  Attach a click listener to the "Add Doctor" button
  When clicked, it opens a modal form using openModal('addDoctor')

  When the DOM is fully loaded:
    - Call loadDoctorCards() to fetch and display all doctors

  Function: loadDoctorCards
  Purpose: Fetch all doctors and display them as cards
    Call getDoctors() from the service layer
    Clear the current content area
    For each doctor returned:
    - Create a doctor card using createDoctorCard()
    - Append it to the content div
    Handle any fetch errors by logging them

  Attach 'input' and 'change' event listeners to the search bar and filter dropdowns
  On any input change, call filterDoctorsOnChange()

  Function: filterDoctorsOnChange
  Purpose: Filter doctors based on name, available time, and specialty
    Read values from the search bar and filters
    Normalize empty values to null
    Call filterDoctors(name, time, specialty) from the service
    If doctors are found:
    - Render them using createDoctorCard()
    If no doctors match the filter:
    - Show a message: "No doctors found with the given filters."
    Catch and display any errors with an alert

  Function: renderDoctorCards
  Purpose: A helper function to render a list of doctors passed to it
    Clear the content area
    Loop through the doctors and append each card to the content area

  Function: adminAddDoctor
  Purpose: Collect form data and add a new doctor to the system
    Collect input values from the modal form
    - Includes name, email, phone, password, specialty, and available times
    Retrieve the authentication token from localStorage
    - If no token is found, show an alert and stop execution
    Build a doctor object with the form values
    Call saveDoctor(doctor, token) from the service
    If save is successful:
    - Show a success message
    - Close the modal and reload the page
    If saving fails, show an error message
*/
