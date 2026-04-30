package com.mycompany.h_m_s.servlets;

import com.mycompany.h_m_s.beans.Patient;
import com.mycompany.h_m_s.hibernateUtil.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.query.Query;
import org.hibernate.Session;
//import org.hibernate.Transaction;

public class UpdatePatientServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Patient</title>");
            out.println("</head>");
            out.println("<body>");

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String contact = request.getParameter("contact");
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            String disease = request.getParameter("disease");
            
                   
            int id = Integer.parseInt(request.getParameter("id"));

            Patient p = new Patient(id, name, email, contact, age, gender, disease);

            //Option 1 By HQL Start
            String HQL
                    = "UPDATE Patient p SET p.name = :name, p.email = :email, p.contact = :contact, "
                    + "p.age = :age, p.gender = :gender, p.disease = :disease "
                    + "WHERE p.id = :id";

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Query query = session.createQuery(HQL);
            query.setParameter("name", name);
            query.setParameter("email", email);
            query.setParameter("contact", contact);
            query.setParameter("age", age);
            query.setParameter("gender", gender);
            query.setParameter("disease", disease);
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
