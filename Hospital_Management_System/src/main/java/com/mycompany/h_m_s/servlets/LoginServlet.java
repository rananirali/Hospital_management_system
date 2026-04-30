package com.mycompany.h_m_s.servlets;

import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;

import com.mycompany.h_m_s.helpers.Helper;
import com.mycompany.h_m_s.beans.Bean;

//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.*;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Bean user = new Bean(email, password);
        
        Helper helper = new Helper();
                
        String role = helper.validateUser(user);
           
        switch (role) {
            case "patient":
                {
                    RequestDispatcher rd = request.getRequestDispatcher("doctorDashboard.jsp");
                    rd.forward(request, response);
                    break;
                }
            case "doctor":
                {
                    RequestDispatcher rd = request.getRequestDispatcher("patientDashboard.jsp");
                    rd.forward(request, response);
                    break;
                }
            case "admin":
                {
                    RequestDispatcher rd = request.getRequestDispatcher("/AdminServlet");
                    rd.forward(request, response);
                    break;
                }    
            default:
                {
                    RequestDispatcher rd = request.getRequestDispatcher("login.html");
                    rd.include(request, response);
                    break;
                }
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
