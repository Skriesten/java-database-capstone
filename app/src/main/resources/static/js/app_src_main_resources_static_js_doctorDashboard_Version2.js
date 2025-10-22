// The code below is from AI CoPilot
// It was inccorporated into doctorDashboard.js

import {getAllAppointments} from "./services/appointmentRecordService.js";
import {loadAppointments} from './appointmentRecord.js';
import {createPatientRow} from "./components/patientRows.js";

// Helper: return today's date string YYYY-MM-DD
function selectADate() {
  const d = new Date();
  const year = d.getFullYear();
  const month = String(d.getMonth() + 1).padStart(2, '0');
  const day = String(d.getDate()).padStart(2, '0');
  return `${year}-${month}-${day}`;
}

// Retrieve token safely when needed (after DOM)
function getToken() {
  return localStorage.getItem('token');
}

// Run after DOM is ready
document.addEventListener('DOMContentLoaded', () => {
  const patientTable = document.getElementById('patientTable');
  const patientTableBody = patientTable ? patientTable.tBodies[0] : null;

  const datePicker = document.getElementById('datepicker');
  if (datePicker) {
    datePicker.value = selectADate();
    datePicker.addEventListener('change', (e) => {
      const newDate = e.target.value.trim();
      loadAppointments(newDate);
    });
  }

  const searchBarEl = document.getElementById('searchBar');
  if (searchBarEl) {
    searchBarEl.addEventListener('input', (e) => {
      const searchBarValue = e.target.value.trim();
      const patientName = searchBarValue.length > 0 ? searchBarValue : null;
      getAllAppointments(datePicker ? datePicker.value : selectADate(), patientName);
    });
  }

  const todayButton = document.getElementById('todayButton');
  if (todayButton) {
    todayButton.addEventListener('click', () => {
      // Optionally set date picker to today
      if (datePicker) datePicker.value = selectADate();
      loadAppointments('today');
    });
  }

  // initial load
  // renderContent() should be safe (it checks getRole()). Call it after header is injected.
  if (typeof renderContent === 'function') {
    try { renderContent(); } catch (e) { console.warn('renderContent error', e); }
  }
  loadAppointments('today');
});