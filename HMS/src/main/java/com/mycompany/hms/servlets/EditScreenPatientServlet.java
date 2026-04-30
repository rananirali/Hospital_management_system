/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.hms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.mycompany.hms.beans.Bean;
import com.mycompany.hms.helper.DBService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EditScreenPatientServlet extends HttpServlet {
    
    private DBService dbService;

    @Override
    public void init() throws ServletException {

        ApplicationContext context
                = new ClassPathXmlApplicationContext("com/mycompany/hms/spring/applicationContext.xml");

        dbService = context.getBean("dbService", DBService.class);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int patient_id = Integer.parseInt(request.getParameter("patient_id"));
        
        Bean b = dbService.fetchPatient(patient_id);
       
        out.println("<html><head><title>EditScreen Patient</title>");
        out.println("<link rel='stylesheet' href='" + request.getContextPath() + "/css/style.css'></head><body>");
        out.println("<div class='main-content'>");
        out.println("<div class='container'>");
        out.println("<h2>Update Patient</h2>");
        out.println("<form method='post' action='UpdatePatientServlet'>");
        out.println("<input type='hidden' name='patient_id' value='" + b.getId() + "'/><br/>");
        out.println("<div class='form-group'><label>Name:</label> <input type='text' name='name' value='" + b.getName() + "'/></div>");
        out.println("<div class='form-group'><label>Email:</label> <input type='email' name='email' value='" + b.getEmail() + "'/></div>");
        out.println("<div class='form-group'><label>Contact No.:</label> <input type='tel' name='mobile' value='" + b.getContact() + "'/></div>");
        out.println("<div class='form-group'><label>Age:</label> <input type='text' name='age' value='" + b.getAge() + "'/></div>");
        out.println("<div class='form-group'><label>Gender:</label> <input type='text' name='gender' value='" + b.getGender() + "'/></div>");
        out.println("<div class='form-group'><label>Disease:</label> <input type='text' name='disease' value='" + b.getDisease() + "'/></div>");
        out.println("<input type='submit' value='Update'/>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body></html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
