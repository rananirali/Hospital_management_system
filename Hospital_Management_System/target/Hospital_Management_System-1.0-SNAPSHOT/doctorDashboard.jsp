<%-- 
    Document   : doctorDashboard
    Created on : 25 Feb 2026, 5:16:35?pm
    Author     : LENOVO
--%>

<%@page import="com.mycompany.h_m_s.beans.Bean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.h_m_s.helpers.Helper"%>
<%@ include file="doctorDashboard.html" %>
<div class="main-content">
<div class="dashboard-container">
    <h3>Doctors List</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Contact No.</th>
            <th>Specialization</th>
            <th>Available Days</th>
            <th>Available Time</th>
        </tr>    
<%
    Helper h = new Helper();
    ArrayList<Bean> list = h.getAllDoctors();

    request.setAttribute("doctorList", list);

    if (list != null) {
        for (Bean d : list) {
%>
<tr>
    <td><%= d.getId()%></td>
    <td><%= d.getName()%></td>
    <td><%= d.getEmail()%></td>
    <td><%= d.getContact()%></td>
    <td><%= d.getSpecialization()%></td>
    <td><%= d.getAvailabledays()%></td>
    <td><%= d.getAvailabletime()%></td>
</tr>
<br>
<%
    }%> 
</table>
</div> 
</div>
    
    <footer class="footer">
            <p>2026 Hospital Management System</p>
        </footer>
<%
    }
%>
