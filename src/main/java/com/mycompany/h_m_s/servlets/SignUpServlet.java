package com.mycompany.h_m_s.servlets;

import com.mycompany.h_m_s.beans.Doctor;
import com.mycompany.h_m_s.beans.Patient;
import com.mycompany.h_m_s.hibernateUtil.HibernateUtil;
import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;
import org.hibernate.Session;

public class SignUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String role = request.getParameter("role");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        if ("doctor".equals(role)) {

            Doctor d = new Doctor();

            d.setName(request.getParameter("name"));
            d.setEmail(request.getParameter("email"));
            d.setContact(request.getParameter("contact"));
            d.setPassword(request.getParameter("password"));
//            d.setSpecialization(request.getParameter("specialization"));
//            d.setAvailabledays(request.getParameter("availabledays"));
//            d.setAvailabletime(request.getParameter("availabletime"));

            session.save(d);  

        } 
        else if ("patient".equals(role)) {

            Patient p = new Patient();

            p.setName(request.getParameter("name"));
            p.setEmail(request.getParameter("email"));
            p.setContact(request.getParameter("contact"));
            p.setPassword(request.getParameter("password"));
            
//            String ageStr = request.getParameter("age");
//            if (ageStr != null && !ageStr.isEmpty()) {
//                p.setAge(Integer.valueOf(ageStr));
//            }
//            
//            p.setGender(request.getParameter("gender"));
//            p.setDisease(request.getParameter("disease"));

            session.save(p); 
        }
        
        session.getTransaction().commit();

        RequestDispatcher rd = request.getRequestDispatcher("/login.html");
        rd.forward(request, response);
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
