package com.mycompany.h_m_s.servlets;

import com.mycompany.h_m_s.beans.Doctor;
import com.mycompany.h_m_s.beans.Patient;
import com.mycompany.h_m_s.beans.Admin;
import com.mycompany.h_m_s.hibernateUtil.HibernateUtil;

import java.io.IOException;
import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            Query<Patient> qp = session.createQuery("from Patient where email=:e and password=:p", Patient.class);
            qp.setParameter("e", email);
            qp.setParameter("p", password);

            Patient p = qp.uniqueResult();

            if (p != null) {
                HttpSession hs = request.getSession();
                hs.setAttribute("user", p);
                hs.setAttribute("role", "patient");

                RequestDispatcher rd = request.getRequestDispatcher("/DoctorDashboardServlet");
                rd.forward(request, response);
                return;
            }

            Query<Doctor> qd = session.createQuery(
                    "from Doctor where email=:email and password=:password", Doctor.class);
            qd.setParameter("email", email);
            qd.setParameter("password", password);

            Doctor d = qd.uniqueResult();

            if (d != null) {
                HttpSession hs = request.getSession();
                hs.setAttribute("user", d);
                hs.setAttribute("role", "doctor");

                RequestDispatcher rd = request.getRequestDispatcher("/PatientDashboardServlet");
                rd.forward(request, response);
                return;
            }

            Query<Admin> qA = session.createQuery("from Admin where email=:e and password=:p", Admin.class);
            qA.setParameter("e", email);
            qA.setParameter("p", password);

            Admin a = qA.uniqueResult();

            if (a != null) {
                HttpSession hs = request.getSession();
                hs.setAttribute("user", a);
                hs.setAttribute("role", "admin");

                RequestDispatcher rd = request.getRequestDispatcher("/AdminServlet");
                rd.forward(request, response);
                return;
            }

            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.include(request, response);

        } finally {
            session.getTransaction().commit();
        }
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
