package com.mycompany.hms.servlets;

//import java.io.IOException;
//import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;
import com.mycompany.hms.beans.Bean;
import com.mycompany.hms.helper.DBService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UpdateServlet extends HttpServlet {
    
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
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Doctor</title>");
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
            
           dbService.editDoctor(user);
            
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
