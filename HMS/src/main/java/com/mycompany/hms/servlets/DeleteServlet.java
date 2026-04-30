package com.mycompany.hms.servlets;

//import java.io.IOException;
//import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;
import com.mycompany.hms.helper.DBService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeleteServlet extends HttpServlet {
    
    private DBService dbService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("com/mycompany/hms/spring/applicationContext.xml");

        dbService = context.getBean("dbService", DBService.class);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete Doctor</title>");
            out.println("</head>");
            out.println("<body>");
            int doctor_id = Integer.parseInt(request.getParameter("doctor_id"));
            dbService.deleteDoctor(doctor_id);
                        
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
