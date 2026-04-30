/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function validateSignUp(){

    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let mobile = document.getElementById("mobile").value;
    let password = document.getElementById("password").value;
    let role = document.getElementById("role").value;

    if(name === ""){
        alert("Name is required");
        return false;
    }

    if (email === "") {
        alert("Please enter your email");
        return false;
    }
    
    if(mobile.length !== 10 || isNaN(mobile)){
        alert("Mobile number must be 10 digits");
        return false;
    }

    if(password.length < 6){
        alert("Password must be at least 6 characters");
        return false;
    }

    if(role === ""){
        alert("Please select role");
        return false;
    }

    alert("Sign Up Successfully");
    
    return true;
}

function validateLogin() {

    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    if (email === "") {
        alert("Please enter your email.");
        return false;
    }

    if (password === "") {
        alert("Please enter your password.");
        return false;
    }

    if (password.length < 6) {
        alert("Password must be at least 6 characters.");
        return false;
    }

    return true;
}