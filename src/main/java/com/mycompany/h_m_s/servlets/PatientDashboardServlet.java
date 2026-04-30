package com.mycompany.h_m_s.servlets;

import com.mycompany.h_m_s.beans.Patient;
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

public class PatientDashboardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        List<Patient> list;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            Query<Patient> query = session.createQuery("FROM Patient", Patient.class);
            list = query.list();

            session.getTransaction().commit();
        }

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Dashboard</title>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<header class='header'>");
        out.println("<h2>Patients Dashboard</h2>");
        out.println("</header>");

        out.println("<nav class='navbar'>");
        out.println("<a href='" + request.getContextPath() + "/PatientDashboardServlet'>Dashboard</a>");
        out.println("<a href='index.html'>Logout</a>");
        out.println("</nav>");
     
        out.println("<div class='main-content'>");
        out.println("<div class='dashboard-container'>");
        out.println("<h3>Patients List</h3>");
        out.println("<table border='1'>");

        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Email</th>");
        out.println("<th>Contact No.</th>");
        out.println("<th>Age</th>");
        out.println("<th>Gender</th>");
        out.println("<th>Disease</th>");
        out.println("</tr>");

        for (Patient p : list) {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>" + p.getEmail() + "</td>");
                out.println("<td>" + p.getContact() + "</td>");
                out.println("<td>" + p.getAge() + "</td>");
                out.println("<td>" + p.getGender() + "</td>");
                out.println("<td>" + p.getDisease() + "</td>");
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
