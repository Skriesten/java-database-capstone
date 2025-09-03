
import {API_BASE_URL} from "../config/config";
const DOCTOR_API = API_BASE_URL + '/doctor';

// *** GET DOCTOR FUNCTION ******************
async  function getDoctors(){
    let username;
    let password;
    const doctors = {username, password};
    await fetch(DOCTOR_API, {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({doctors})
        });
} // End of getDoctors function

// ***  DELETE DOCTOR FUNCTION ****************
export async function deleteDoctor(id, token) {
        // 1. Construct the full endpoint URL using a template literal.
        const endpoint = DOCTOR_API + '/${id}${token}';
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
async function saveDoctor(doctor, token) {
    const doctorNew = {name, email, phone, password, role, specialty,date_hired, active,
        salary, medical_license_no, clinic_address, token};

    const response =
            await fetch(DOCTOR_API, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(doctorNew)
            });
    if(!response.ok) {
        response.json().catch(() => ({message: response.statusText}));
    }
    if (!response.ok) {
        const errorData = await response.json().catch(() => ({message: response.statusText}));
        throw new Error(`HTTP Error: ${response.status} - ${errorData.message}`);
    }
} // End of saveDoctor function

// *** FILTER DOCTORS FUNCTION  *************************** //
async  function  filterDoctors(name, time, specialty) {
const response = await fetch(DOCTOR_API, {
    method: 'GET',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({name, time, specialty})
});
    const params = new URLSearchParams();

    // Add parameters only if they are provided and not empty
    if (name) {
        params.append('name', name);
    }
    if (time) {
        params.append('time', time);
    }
    if (specialty) {
        params.append('specialty', specialty);
    }
    // Construct the full URL with query parameters
    const requestUrl = DOCTOR_API + `?${params.toString()}`;
    try {
        const response = await fetch(DOCTOR_API, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({name, time, specialty})
        });
        // Return the list of doctors
        return await response.json();

        // Check for HTTP errors (e.g., 404 Not Found, 500 Server Error)
        if (!response.ok) {
            // Throw an error with the status code for specific handling
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
    } catch (error) {
        // Handle network errors or other issues with the request
        console.error('Failed to filter doctors:', error);
        alert(`Error: Could not retrieve doctors. ${error.message}`);
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
