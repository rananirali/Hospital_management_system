package com.mycompany.h_m_s.servlets;

import com.mycompany.h_m_s.beans.Bean;
import com.mycompany.h_m_s.helpers.Helper;

import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
       
        PrintWriter out = response.getWriter();

        Helper h = new Helper();

        out.println("<html><head><title>Dashboard</title><link rel='stylesheet' href='css/style.css'></head><body>");
        out.println("<header class='header'><h2>Admins Dashboard</h2></header>");
        out.println("<nav class='navbar'><a href='AdminServlet'>Dashboard</a><a href='index.html'>Logout</a></nav>");
        out.println("<div class='main-content'>");
        out.println("<div class='dashboard-container'>"); 
        out.println("<h3>Doctors List</h3>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th><th>Name</th><th>Email</th><th>Contact No.</th><th>Specialization</th><th>Available Days</th><th>Available Time</th><th>Actions</th>");
        out.println("</tr>");

        ArrayList<Bean> doctors = h.getAllDoctors();

        for (Bean d : doctors) {
            out.println("<tr>");
            out.println("<td>" + d.getId() + "</td>");
            out.println("<td>" + d.getName() + "</td>");
            out.println("<td>" + d.getEmail() + "</td>");
            out.println("<td>" + d.getContact() + "</td>");
            out.println("<td>" + d.getSpecialization() + "</td>");
            out.println("<td>" + d.getAvailabledays() + "</td>");
            out.println("<td>" + d.getAvailabletime() + "</td>");
            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/EditServlet?doctor_id=" + d.getId() + "'>Edit</a> | ");
            out.println("<a href='" + request.getContextPath() + "/DeleteServlet?doctor_id=" + d.getId() + "' onclick=\"return confirm('Are you sure?')\">Delete</a>");
            out.println("</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<br><br>");
        out.println("<h3>Patients List</h3>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th><th>Name</th><th>Email</th><th>Contact No.</th><th>Age</th><th>Gender</th><th>Disease</th><th>Actions</th>");
        out.println("</tr>");

        ArrayList<Bean> patients = h.getAllPatients();

        for (Bean p : patients) {
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getName() + "</td>");
            out.println("<td>" + p.getEmail() + "</td>");
            out.println("<td>" + p.getContact() + "</td>");
            out.println("<td>" + p.getAge() + "</td>");
            out.println("<td>" + p.getGender() + "</td>");
            out.println("<td>" + p.getDisease() + "</td>");
            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/EditPatientServlet?patient_id=" + p.getId() + "'>Edit</a> | ");
            out.println("<a href='" + request.getContextPath() + "/DeletePatientServlet?patient_id=" + p.getId() + "' onclick=\"return confirm('Are you sure?')\">Delete</a>");
            out.println("</td>");
            out.println("</tr>");
        }
        
        out.println("</table>");
        out.println("</div>");
        out.println("</div>");
        out.println("<footer class='footer'><p>2026 Hospital Management System</p></footer>");
        out.println("</body>");
        out.println("</html>");
        }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
