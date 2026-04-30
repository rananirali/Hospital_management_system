package com.mycompany.h_m_s.servlets;

//import java.io.IOException;
//import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;
import com.mycompany.h_m_s.beans.Bean;
import com.mycompany.h_m_s.helpers.Helper;

public class EditServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int doctor_id = Integer.parseInt(request.getParameter("doctor_id"));
        
        Helper h = new Helper();
        Bean b = h.fetchDoctor(doctor_id);

        out.println("<html><head><title>Edit Doctor</title>");
        out.println("<link rel='stylesheet' href='" + request.getContextPath() + "/css/style.css'></head><body>");
         out.println("<div class='main-content'>");
        out.println("<div class='container'>");
        out.println("<h2>Edit Doctor</h2>");
        
        out.println("<form method='post' action='FinalEditServlet'>");
        out.println("<input type='hidden' name='doctor_id' value='" + b.getId() + "'/><br/>");
        out.println("<div class='form-group'><label>Name:</label> <input type='text' name='name' value='" + b.getName() + "'/></div>");
        out.println("<div class='form-group'><label>Email:</label> <input type='email' name='email' value='" + b.getEmail() + "'/></div>");
        out.println("<div class='form-group'><label>Contact No.:</label> <input type='tel' name='mobile' value='" + b.getContact() + "'/></div>");
        out.println("<div class='form-group'><label>Specialization:</label> <input type='text' name='specialization' value='" + b.getSpecialization() + "'/></div>");
        out.println("<div class='form-group'><label>Available Days:</label> <input type='text' name='availabledays' value='" + b.getAvailabledays() + "'/></div>");
        out.println("<div class='form-group'><label>Available Time:</label> <input type='text' name='availabletime' value='" + b.getAvailabletime() + "'/></div>");
        out.println("<input type='submit' value='Update'/>");
        out.println("</form>");
        out.println("</div>");
          out.println("</div>");
        out.println("</body></html>");
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
