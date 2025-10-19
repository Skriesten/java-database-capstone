// src/main/resources/static/js/components/header.js
// Cleaned-up header renderer and safe handling when token is missing.
//
// Note: this file uses ES module imports. Include it in your Thymeleaf template with:
// <script type="module" th:src="@{/js/components/header.js}" defer></script>

import { openModal } from "./modals.js";
import { patientSignup, patientLogin } from "../services/patientServices.js";

window.myHeader = window.myHeader || {};

export function renderHeader() {
  const headerDiv = document.getElementById("header");
  if (!headerDiv) return;

  // If on root/index page, clear role/token and render minimal header then stop
  if (window.location.pathname === "/" || window.location.pathname.endsWith("/index.html")) {
    localStorage.removeItem("userRole");
    localStorage.removeItem("token");
    headerDiv.innerHTML = `
      <header class="header" id="header">
        <div class="logo-img">
          <img src="/assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
          <span class="logo-title">Smart Clinic Management System</span>
        </div>
      </header>`;
    // No further initialization needed on index/root
    return;
  }

  const role = localStorage.getItem("userRole");
  const token = localStorage.getItem("token");

  // If role exists but token is missing, *do not* immediately redirect.
  // Instead clear the role and render a header with login/signup links so the user can sign in.
  if ((role === "loggedPatient" || role === "admin" || role === "doctor") && !token) {
    localStorage.removeItem("userRole");
    // Render header that prompts user to log in (avoid auto-redirect that causes the flash)
    const fallbackContent = `
      <header class="header" id="header">
        <div class="logo-img">
          <img src="/assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
          <span class="logo-title">Smart Clinic Management System</span>
        </div>
        <nav class="header-nav">
          <a id="loginLink" href="/login">Log in</a>
          <a id="signupLink" href="/signup">Sign up</a>
        </nav>
      </header>`;
    headerDiv.innerHTML = fallbackContent;
    attachHeaderButtonListeners(); // attach handlers for fallback links
    return;
  }

  // Build dynamic header content for valid sessions or anonymous users
  let dynamicContent = `
    <header class="header" id="header">
      <div class="logo-img">
        <img src="/assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
        <span class="logo-title">Smart Clinic Management System</span>
      </div>`;

  if (role === "admin") {
    dynamicContent += `
      <nav class="header-nav">
        <button id="addDocBtn" class="adminBtn">Add Doctor</button>
        <a id="logoutLink" href="#">Logout</a>
      </nav>`;
  } else if (role === "doctor") {
    dynamicContent += `
      <nav class="header-nav">
        <a id="doctorHomeLink" href="/doctor/doctorDashboard.html">Home</a>
        <a id="logoutLink" href="#">Logout</a>
      </nav>`;
  } else if (role === "patient") {
    dynamicContent += `
      <nav class="header-nav">
        <a id="patientLoginLink" href="/pages/loggedPatientDashboard.html" class="anchor-link">Login</a>
        <a id="patientSignupLink" href="/signup">Sign Up</a>
      </nav>`;
  } else if (role === "loggedPatient") {
    dynamicContent += `
      <nav class="header-nav">
        <a id="patientHomeLink" href="/pages/loggedPatientDashboard.html">Home</a>
        <a id="appointmentsLink" href="/pages/patientAppointments.html">Appointments</a>
        <a id="patientLogoutLink" href="#">Logout</a>
      </nav>`;
  } else {
    // anonymous user
    dynamicContent += `
      <nav class="header-nav">
        <a id="loginLink" href="/login">Log in</a>
        <a id="signupLink" href="/signup">Sign up</a>
      </nav>`;
  }

  dynamicContent += `</header>`;
  headerDiv.innerHTML = dynamicContent;

  attachHeaderButtonListeners();
}

// Attach event listeners in a single, clear function
function attachHeaderButtonListeners() {
  // Logout link(s)
  const logoutLink = document.getElementById("logoutLink") || document.getElementById("patientLogoutLink");
  if (logoutLink) {
    logoutLink.addEventListener("click", function (e) {
      e.preventDefault();
      logout();
    });
  }

  // Add Doctor button (admin)
  const addDocBtn = document.getElementById("addDocBtn");
  if (addDocBtn) {
    addDocBtn.addEventListener("click", function (e) {
      e.preventDefault();
      if (typeof openModal === "function") openModal("addDoctor");
    });
  }

  // Doctor home
  const doctorHomeLink = document.getElementById("doctorHomeLink");
  if (doctorHomeLink) {
    doctorHomeLink.addEventListener("click", function (e) {
      // let the link navigate naturally or customize if needed
    });
  }

  // Patient login/signup
  const patientLoginLink = document.getElementById("patientLoginLink");
  if (patientLoginLink) {
    patientLoginLink.addEventListener("click", function (e) {
      // default navigation handled by the anchor; if you need an AJAX login call, do it here
    });
  }

  const patientSignupLink = document.getElementById("patientSignupLink");
  if (patientSignupLink) {
    patientSignupLink.addEventListener("click", function (e) {
      // default navigation will go to signup page
    });
  }

  // Patient home & appointments
  const patientHomeLink = document.getElementById("patientHomeLink");
  if (patientHomeLink) {
    patientHomeLink.addEventListener("click", function (e) {
      // let default navigation happen
    });
  }

  // Expose a small API if other code needs it
  window.myHeader = window.myHeader || {};
  window.myHeader.logout = logout;
  window.myHeader.home = home;
}

// Utility navigation functions
function logout() {
  localStorage.removeItem("userRole");
  localStorage.removeItem("token");
  // Prefer explicit file path; index is the landing page
  window.location.href = "/index.html";
}

function logoutPatient() {
  localStorage.removeItem("token");
  localStorage.setItem("userRole", "patient");
  window.location.href = "/pages/patientDashboard.html";
}

function home() {
  window.location.href = "/index.html";
}

function appointments() {
  window.location.href = "/pages/patientAppointments.html";
}

// Initialize header when DOM is ready
if (document.readyState === "loading") {
  document.addEventListener("DOMContentLoaded", renderHeader);
} else {
  renderHeader();
}