
import {API_BASE_URL} from "../config/config.js";
const DOCTOR_API = API_BASE_URL + '/doctor';

// *** GET DOCTOR FUNCTION ******************
export async  function getDoctors() {
    try {
        let username;
        let password;
        const doctors = {username, password};
        await fetch(`${DOCTOR_API}`,
            {
                method: 'GET',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({doctors})
            });
    } catch (error) {
        console.log(error.message);
        alert("Could not find doctor");
    }
}  // End of getDoctors function

// ***  DELETE DOCTOR FUNCTION ****************
export async function deleteDoctor(id, token) {
        // 1. Construct the full endpoint URL using a template literal.
        const endpoint = `${DOCTOR_API} /${id}${token}`;
        // 2. Send the DELETE request with the authentication token.
        const response =
            await fetch(endpoint, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`, // Pass the token in the Authorization header
                'Content-Type': 'application/json' // Assuming the API expects JSON
            },
        });
        // 3. Handle HTTP errors (e.g., 404 Not Found, 500 Server Error).
        if (!response.ok) {
            const errorData = await response.json().catch(() => ({message: response.statusText}));
            throw new Error(`HTTP Error: ${response.status} - ${errorData.message}`);
        }
} // End of deleteDoctor function

// *** SAVE DOCTOR FUNCTION  ***************************
export async function saveDoctor(doctor, token) {
    const doctorNew = {name, email, phone, password, role, specialty,date_hired, active,
        salary, medical_license_no, clinic_address, token};
    try {
    const response =
            await fetch(`${DOCTOR_API}/create`,
            {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(doctorNew)
            });

        if (response.ok) {
            const data = await response.json();
        } else {
            const errorData = await response.json().catch(() => ({message: response.statusText}));
            console.error(`HTTP Error: ${response.status} - ${errorData.message}`);
        }
    }catch(error) {
        alert("Could not create Doctor");
    }
}   // End of saveDoctor function


// *** FILTER DOCTORS FUNCTION  *************************** //
export async  function  filterDoctors(name, time, specialty) {

    try {
        const response = await fetch(`${DOCTOR_API}/filter/$(name)/$(time)/$(specialty)`, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        });

        // Return the list of doctors if response is OK
        if (response.ok) {
            const data = await response.json();
            return data;
        } else {
            console.error("Failed to fetch doctors", response.statusText);
            // Throw an error with the status code for specific handling
            return [];
        }
    } catch (error) {
        console.error('Failed to filter doctors:', error);
        // Return an empty list to prevent the application from crashing
        return [];
    }
}  // End of filterDoctors function

/* **********  INSTRUCTIONS  ****************************** //
  Import the base API URL from the config file
  Define a constant DOCTOR_API to hold the full endpoint for doctor-related actions

  Function: getDoctors
  Purpose: Fetch the list of all doctors from the API

   Use fetch() to send a GET request to the DOCTOR_API endpoint
   Convert the response to JSON
   Return the 'doctors' array from the response
   If there's an error (e.g., network issue), log it and return an empty array

export function deleteDoctor(){}
  Function: deleteDoctor
  Purpose: Delete a specific doctor using their ID and an authentication token

   Use fetch() with the DELETE method
    - The URL includes the doctor ID and token as path parameters
   Convert the response to JSON
   Return an object with:
    - success: true if deletion was successful
    - message: message from the server
   If an error occurs, log it and return a default failure response


  Function: saveDoctor
  Purpose: Save (create) a new doctor using a POST request

   Use fetch() with the POST method
    - URL includes the token in the path
    - Set headers to specify JSON content type
    - Convert the doctor object to JSON in the request body

   Parse the JSON response and return:
    - success: whether the request succeeded
    - message: from the server

   Catch and log errors
    - Return a failure response if an error occurs


  Function: filterDoctors
  Purpose: Fetch doctors based on filtering criteria (name, time, and specialty)

   Use fetch() with the GET method
    - Include the name, time, and specialty as URL path parameters
   Check if the response is OK
    - If yes, parse and return the doctor data
    - If no, log the error and return an object with an empty 'doctors' array

   Catch any other errors, alert the user, and return a default empty result
*/
