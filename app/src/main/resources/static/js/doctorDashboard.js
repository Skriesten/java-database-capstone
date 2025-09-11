
import {getAllAppointments} from "./services/appointmentRecordService.js";
import {loadAppointments} from './appointmentRecord.js';
import {createPatientRow} from "./components/patientRows.js";

// Below is creating the variable for identifying the table.
const patientTable = document.getElementById('patientTable');
// Below is accessing the table body
const patientTableBody= patientTable.tBodies[0];

const datePicker = document.getElementById('datePicker');
datePicker.value = selectADate();
const selectedDate = new Date();

// Function for populating the search box with today's date in format 'YYYY-MM-DD'
function selectADate(selectedDate) {
      const year = selectedDate.getFullYear();
    const month = String(selectedDate.getMonth() + 1).padStart(2, '0');
    const day = String(selectedDate.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
}  // End of select date function

// Retrieving the token
const token = localStorage.getItem('token');


//  Handling the Search Bar
let patientName = null;
const searchBar = document.getElementById('searchBar').addEventListener('input', (e) => {
    let searchBarValue = e.target.value.trim();
    let patientName = null;
    if(searchBarValue.length > 0){
        patientName = searchBarValue;
    } else {
        patientName = null;
    }
return getAllAppointments(datePicker.value, patientName);
});

// Handling the 'TODAY' button
const todayButton = document.getElementById('todayButton');
todayButton.addEventListener('click', ()=>{
    selectedDateToday()
    loadAppointments();
})

//  Handling the Date Picker
datePicker.addEventListener('change', (e) => {
  const newDate = e.target.value.trim();
    loadAppointments(newDate);
});

//  Call the imported loadAppointments function
loadAppointments("upcoming");

//  Once page is fully loaded - do the following:
document.addEventListener('DOMContentLoaded', ()=>{
    renderContent();
    loadAppointments("today");
})


/* ******* INSTRUCTIONS *********************************
  Import getAllAppointments to fetch appointments from the backend
  Import createPatientRow to generate a table row for each patient appointment

  Get the table body where patient rows will be added  DONE
  Initialize selectedDate with today's date in 'YYYY-MM-DD' format  DONE
  Get the saved token from localStorage (used for authenticated API calls)  DONE
  Initialize patientName to null (used for filtering by name) DONE

  Add an 'input' event listener to the search bar
  On each keystroke:
    - Trim and check the input value
    - If not empty, use it as the patientName for filtering
    - Else, reset patientName to "null" (as expected by backend)
    - Reload the appointments list with the updated filter


  Add a click listener to the "Today" button    DONE
  When clicked:
    - Set selectedDate to today's date  DONE
    - Update the date picker UI to match    DONE
    - Reload the appointments for today     DONE


  Add a change event listener to the date picker
  When the date changes:
    - Update selectedDate with the new value
    - Reload the appointments for that specific date


  Function: loadAppointments
  Purpose: Fetch and display appointments based on selected date and optional patient name

  Step 1: Call getAllAppointments with selectedDate, patientName, and token
  Step 2: Clear the table body content before rendering new rows

  Step 3: If no appointments are returned:
    - Display a message row: "No Appointments found for today."

  Step 4: If appointments exist:
    - Loop through each appointment and construct a 'patient' object with id, name, phone, and email
    - Call createPatientRow to generate a table row for the appointment
    - Append each row to the table body

  Step 5: Catch and handle any errors during fetch:
    - Show a message row: "Error loading appointments. Try again later."


  When the page is fully loaded (DOMContentLoaded):
    - Call renderContent() (assumes it sets up the UI layout)
    - Call loadAppointments() to display today's appointments by default
*/
