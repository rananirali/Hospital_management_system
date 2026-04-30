package com.mycompany.h_m_s.servlets;

import com.mycompany.h_m_s.beans.Doctor;
import com.mycompany.h_m_s.hibernateUtil.HibernateUtil;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.Iterator;
//import java.util.List;
import java.util.*;
import org.hibernate.query.Query;
import org.hibernate.Session;

public class EditScreenServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();            
        String HQL = "FROM Doctor d WHERE d.id = :id ";
        Query query = session.createQuery(HQL);
        query.setParameter("id", id);
        List results = query.list();
        session.getTransaction().commit();
        
        Doctor d = null;
        for (Iterator iterator = results.iterator(); iterator.hasNext();)
        {
            d = (Doctor) iterator.next();
        }
        
        out.println("<html><head><title>EditScreen Doctor</title>");
        out.println("<link rel='stylesheet' href='" + request.getContextPath() + "/css/style.css'></head><body>");
        
        out.println("<div class='main-content'>");
        out.println("<div class='container'>");   
        out.println("<h3>Edit Doctor</h3>");
        out.println("<form method='post' action='" + request.getContextPath() + "/UpdateServlet'>");
        out.println("<input type='hidden' name='id' value='" + d.getId() + "'/><br/>");
        out.println("<div class='form-group'><label>Name:</label> <input type='text' name='name' value='" + d.getName() + "'/></div>");
        out.println("<div class='form-group'><label>Email:</label> <input type='email' name='email' value='" + d.getEmail() + "'/></div>");
        out.println("<div class='form-group'><label>Contact No.:</label> <input type='tel' name='contact' value='" + d.getContact() + "'/></div>");
        out.println("<div class='form-group'><label>Specialization:</label> <input type='text' name='specialization' value='" + d.getSpecialization() + "'/></div>");
        out.println("<div class='form-group'><label>Available Days:</label> <input type='text' name='availabledays' value='" + d.getAvailabledays() + "'/></div>");
        out.println("<div class='form-group'><label>Available Time:</label> <input type='text' name='availabletime' value='" + d.getAvailabletime() + "'/></div>");
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