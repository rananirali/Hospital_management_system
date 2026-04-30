package com.mycompany.h_m_s.servlets;

import com.mycompany.h_m_s.beans.Doctor;
import com.mycompany.h_m_s.hibernateUtil.HibernateUtil;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.query.Query;
import org.hibernate.Session;
//import org.hibernate.Transaction;

public class UpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Doctor</title>");
            out.println("</head>");
            out.println("<body>");

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String contact = request.getParameter("contact");
            String specialization = request.getParameter("specialization");
            String availabledays = request.getParameter("availabledays");
            String availabletime = request.getParameter("availabletime");
            int id = Integer.parseInt(request.getParameter("id"));

            Doctor d = new Doctor(id, name, email, contact, specialization, availabledays, availabletime);

            //Option 1 By HQL Start
            String HQL
                    = "UPDATE Doctor d SET d.name = :name, d.email = :email, d.contact = :contact, "
                    + "d.specialization = :spec, d.availabledays = :days, d.availabletime = :time "
                    + "WHERE d.id = :id";

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Query query = session.createQuery(HQL);
            query.setParameter("name", name);
            query.setParameter("email", email);
            query.setParameter("contact", contact);
            query.setParameter("spec", specialization);
            query.setParameter("days", availabledays);
            query.setParameter("time", availabletime);
            query.setParameter("id", id);
            query.executeUpdate(); 
            session.getTransaction().commit();
            session.close();

            /*            
            //Option 2 By Object Start
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();            
            session.update(lb);
            tx.commit();
            session.close();
            //Option 2 By Object End
             */
//            RequestDispatcher rd = request.getRequestDispatcher("/AdminServlet");
//            rd.include(request, response);
            response.sendRedirect(request.getContextPath() + "/AdminServlet");
            out.println("</body>");
            out.println("</html>");
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
