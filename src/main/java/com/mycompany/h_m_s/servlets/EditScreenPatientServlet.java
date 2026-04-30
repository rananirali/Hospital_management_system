package com.mycompany.h_m_s.servlets;

import com.mycompany.h_m_s.beans.Patient;
import com.mycompany.h_m_s.hibernateUtil.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;

public class EditScreenPatientServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();            
        String HQL = "FROM Patient p WHERE p.id = :id ";
        Query query = session.createQuery(HQL);
        query.setParameter("id", id);
        List results = query.list();
        session.getTransaction().commit();
        
        Patient p = null;
        for (Iterator iterator = results.iterator(); iterator.hasNext();)
        {
            p = (Patient) iterator.next();
        }
        
        out.println("<html><head><title>EditScreen Patient</title>");
        out.println("<link rel='stylesheet' href='" + request.getContextPath() + "/css/style.css'></head><body>");
        
        out.println("<div class='main-content'>");
        out.println("<div class='container'>");   
        out.println("<h3>Edit Patient</h3>");
        out.println("<form method='post' action='" + request.getContextPath() + "/UpdatePatientServlet'>");
        out.println("<input type='hidden' name='id' value='" + p.getId() + "'/><br/>");
        out.println("<div class='form-group'><label>Name:</label> <input type='text' name='name' value='" + p.getName() + "'/></div>");
        out.println("<div class='form-group'><label>Email:</label> <input type='email' name='email' value='" + p.getEmail() + "'/></div>");
        out.println("<div class='form-group'><label>Contact No.:</label> <input type='tel' name='contact' value='" + p.getContact() + "'/></div>");
        out.println("<div class='form-group'><label>Age:</label> <input type='text' name='age' value='" + p.getAge() + "'/></div>");
        out.println("<div class='form-group'><label>Gender:</label> <input type='text' name='gender' value='" + p.getGender() + "'/></div>");
        out.println("<div class='form-group'><label>Disease:</label> <input type='text' name='disease' value='" + p.getDisease() + "'/></div>");
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