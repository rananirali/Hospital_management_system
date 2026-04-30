package com.mycompany.hms.servlets;

import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;
import com.mycompany.hms.helper.DBService;
import com.mycompany.hms.beans.Bean;
import jakarta.servlet.*;
//import jakarta.servlet.RequestDispatcher;

import java.sql.Connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SignUpServlet extends HttpServlet {

    private DBService dbService;

    @Override
    public void init() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("com/mycompany/hms/spring/applicationContext.xml");

        dbService = context.getBean("dbService", DBService.class);
    }

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

        Connection con = dbService.getConnection();
        dbService.insertUser(con, user);

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
