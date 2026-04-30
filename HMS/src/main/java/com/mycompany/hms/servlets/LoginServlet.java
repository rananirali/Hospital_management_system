package com.mycompany.hms.servlets;

import com.mycompany.hms.beans.Bean;
import com.mycompany.hms.helper.DBService;
//import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
//import jakarta.servlet.ServletException;
import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class LoginServlet extends HttpServlet {

    private DBService dbService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/mycompany/hms/spring/applicationContext.xml");

        dbService = context.getBean("dbService", DBService.class);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Bean user = new Bean(request.getParameter("email"), request.getParameter("password"));

        String role = dbService.validateUser(user);

        switch (role) {
            case "patient": {
                ArrayList<Bean> list = dbService.getAllDoctors();
                request.setAttribute("doctorList", list);
                RequestDispatcher rd = request.getRequestDispatcher("/DoctorDashboardServlet");
                rd.forward(request, response);
                break;
            }
            case "doctor": {
                ArrayList<Bean> list = dbService.getAllPatients();
                request.setAttribute("patientList", list);
                RequestDispatcher rd = request.getRequestDispatcher("/PatientDashboardServlet");
                rd.forward(request, response);
                break;
            }
            case "admin": {
                RequestDispatcher rd = request.getRequestDispatcher("/AdminServlet");
                rd.forward(request, response);
                break;
            }
            default: {
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
