package com.mycompany.h_m_s.servlets;

import java.io.IOException;
//import jakarta.servlet.ServletException;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

import com.mycompany.h_m_s.helpers.Helper;
import com.mycompany.h_m_s.beans.Bean;

//import jakarta.servlet.RequestDispatcher;

import java.sql.Connection;

public class SignUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String contact = request.getParameter("mobile");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        Bean user = new Bean();
        user.setName(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setPassword(password);
        user.setRole(role);

        Connection con = Helper.getConnection();
        Helper.insertUser(con, user);

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
