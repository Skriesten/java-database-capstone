// index.js  API/ Logic Handlers

// Import Required Modules : At the top of the file, import:
// • The openModal function from the modal component file ../components/modals.js.
//     The API_BASE_URL constant from your configuration file ../config/config.js.

import {openModal} from "../components/modals.js";
import {API_BASE_URL} from "../config/config.js";
//import {BASE_URL}  from "../config/config.js";

// define two constants:
    const ADMIN_API = API_BASE_URL + '/admin';
    const DOCTOR_API = API_BASE_URL + '/doctor/login';

// Use window.onload to ensure the script runs after the page is fully loaded.
//     Select the Admin and Doctor login buttons by their id attributes.
//     Attach click event listeners to these buttons.

    window.onload = function() {
        const adminBtn = document.getElementById('adminBtn');
        const doctorBtn = document.getElementById('doctorBtn');

        if (adminBtn) {
            adminBtn.addEventListener('click', (e) => {
                openModal('adminLogin');
            });
        }
        if (doctorBtn) {
            doctorBtn.addEventListener('click',  () => {
                // Code to execute when Doctor button is clicked
                openModal('doctorLogin');
            });
        }
    }

export async function adminLoginHandler() {
        let username;
        let password;
        let role;
        const admin = {username, password};
       try {
               await fetch(ADMIN_API, {
                   method: 'POST',
                   headers: {'Content-Type': 'application/json'},
                   body: JSON.stringify(admin)
               });
           localStorage.setItem('role', JSON.stringify(role));
           selectRole(admin);
       }catch (error) {
           console.error(error)
           alert("Invalid credentials");
       }
    }

export async function doctorLoginHandler(){
    try {
        // 1. Read the email and password values from the input fields
        const email = document.querySelector('#doctor-email').value;
        const password = document.querySelector('#doctor-password').value;
        // 2. Create a doctor object with these values
        const doctor = { email, password };
        // 3. Send a POST request to the Doctor login endpoint
        const response =
                await fetch(DOCTOR_API, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(doctor),
        });
        // Handle server responses
        if (response.ok) {
            // 4. On success: Store the received token in localStorage
            const data = await response.json();
            localStorage.setItem('token', data.token);

            // 5. Call a helper function selectRole() with "doctor" to save the selected role
            // Assuming a `selectRole` function is defined globally (e.g., in render.js)
            if (typeof selectRole === 'function') {
                selectRole('doctor');
            } else {
                alert('Invalid credentials, cannot go any further.');
            }

            // Optional: Redirect or update UI after successful login
            window.location.href = '/doctorDashboard.html';
        } else {
            // 6. On failure: alert the user about invalid credentials
            const errorData = await response.json();
            alert(`Login failed: ${errorData.message}`);
        }
    } catch (error) {
        // 7. Handle unexpected issues using try-catch
        console.error('An unexpected error occurred during doctor login:', error);
        alert('An error occurred during login. Please try again.');
    }
}

// Expose the function globally, if necessary, by attaching it to the window object.
// This allows the function to be called from a button's `onclick` event in index.html.
window.doctorLoginHandler = doctorLoginHandler;





/*
  Import the openModal function to handle showing login popups/modals
  Import the base API URL from the config file
  Define constants for the admin and doctor login API endpoints using the base URL

  Use the window.onload event to ensure DOM elements are available after page load
  Inside this function:
    - Select the "adminLogin" and "doctorLogin" buttons using getElementById
    - If the admin login button exists:
        - Add a click event listener that calls openModal('adminLogin') to show the admin login modal
    - If the doctor login button exists:
        - Add a click event listener that calls openModal('doctorLogin') to show the doctor login modal


  Define a function named adminLoginHandler on the global window object
  This function will be triggered when the admin submits their login credentials

  Step 1: Get the entered username and password from the input fields
  Step 2: Create an admin object with these credentials

  Step 3: Use fetch() to send a POST request to the ADMIN_API endpoint
    - Set method to POST
    - Add headers with 'Content-Type: application/json'
    - Convert the admin object to JSON and send in the body

  Step 4: If the response is successful:
    - Parse the JSON response to get the token
    - Store the token in localStorage
    - Call selectRole('admin') to proceed with admin-specific behavior

  Step 5: If login fails or credentials are invalid:
    - Show an alert with an error message

  Step 6: Wrap everything in a try-catch to handle network or server errors
    - Show a generic error message if something goes wrong


  Define a function named doctorLoginHandler on the global window object
  This function will be triggered when a doctor submits their login credentials

  Step 1: Get the entered email and password from the input fields
  Step 2: Create a doctor object with these credentials

  Step 3: Use fetch() to send a POST request to the DOCTOR_API endpoint
    - Include headers and request body similar to admin login

  Step 4: If login is successful:
    - Parse the JSON response to get the token
    - Store the token in localStorage
    - Call selectRole('doctor') to proceed with doctor-specific behavior

  Step 5: If login fails:
    - Show an alert for invalid credentials

  Step 6: Wrap in a try-catch block to handle errors gracefully
    - Log the error to the console
    - Show a generic error message
*/
