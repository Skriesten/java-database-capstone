// util.js
  function setRole(role) {
    localStorage.setItem("userRole", role);
  }
  
 export function getRole() {
    return localStorage.getItem("userRole");
  }
  
  function clearRole() {
    localStorage.removeItem("userRole");
  }
  
