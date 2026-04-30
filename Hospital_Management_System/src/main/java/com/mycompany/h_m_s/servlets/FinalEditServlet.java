package com.mycompany.h_m_s.servlets;

//import java.io.IOException;
//import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;

import com.mycompany.h_m_s.beans.Bean;
import com.mycompany.h_m_s.helpers.Helper;

public class FinalEditServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FinalEdit Doctor</title>");
            out.println("</head>");
            out.println("<body>");
            
            Bean user = new Bean(Integer.parseInt(request.getParameter("doctor_id")),
                    request.getParameter("name"), 
                    request.getParameter("email"),
                    request.getParameter("mobile"),
                    request.getParameter("specialization"),
                    request.getParameter("availabledays"),
                    request.getParameter("availabletime")
            );
            
            Helper h = new Helper();
            
            h.editDoctor(user);
            
            response.sendRedirect("AdminServlet");
            
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
