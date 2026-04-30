package com.mycompany.h_m_s.servlets;

import com.mycompany.h_m_s.beans.Doctor;
import com.mycompany.h_m_s.hibernateUtil.HibernateUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class DoctorDashboardServlet extends HttpServlet {

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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
         List<Doctor> list;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            Query<Doctor> query = session.createQuery("FROM Doctor", Doctor.class);
            list = query.list();

            session.getTransaction().commit();
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Dashboard</title>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<header class='header'>");
        out.println("<h2>Doctors Dashboard</h2>");
        out.println("</header>");

        out.println("<nav class='navbar'>");
        out.println("<a href='" + request.getContextPath() + "/DoctorDashboardServlet'>Dashboard</a>");
        out.println("<a href='index.html'>Logout</a>");
        out.println("</nav>");

        out.println("<div class='main-content'>");
        out.println("<div class='dashboard-container'>");
        out.println("<h3>Doctors List</h3>");
        
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Email</th>");
        out.println("<th>Contact No.</th>");
        out.println("<th>Specialization</th>");
        out.println("<th>Available Days</th>");
        out.println("<th>Available Time</th>");
        out.println("</tr>");

        for (Doctor d : list) {
            out.println("<tr>");
            out.println("<td>" + d.getId() + "</td>");
            out.println("<td>" + d.getName() + "</td>");
            out.println("<td>" + d.getEmail() + "</td>");
            out.println("<td>" + d.getContact() + "</td>");
            out.println("<td>" + d.getSpecialization() + "</td>");
            out.println("<td>" + d.getAvailabledays() + "</td>");
            out.println("<td>" + d.getAvailabletime() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</div>");
        out.println("</div>");

        out.println("<footer class='footer'>");
        out.println("<p>2026 Hospital Management System</p>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
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
