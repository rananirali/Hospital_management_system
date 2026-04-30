package com.mycompany.h_m_s.servlets;

import com.mycompany.h_m_s.hibernateUtil.HibernateUtil;
import jakarta.servlet.RequestDispatcher;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete Doctor</title>");
            out.println("</head>");
            out.println("<body>");
            
            int id = Integer.parseInt(request.getParameter("id"));
            
            //Option 1 By HQL Start
            String HQL = "DELETE FROM Doctor d "
                    + " WHERE d.id = :id";
            
            int rowCount;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery(HQL);
                query.setParameter("id", id);
                rowCount = query.executeUpdate();
                tx.commit();
            }
         
            /*            
            //Option 2 By Object Start
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            
            LoginBean lb = new LoginBean();
            lb.setId(id);
            
            session.delete(lb);
            
            tx.commit();
            session.close();
            */
           
            RequestDispatcher rd = request.getRequestDispatcher("/AdminServlet");
            rd.include(request, response);
            
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