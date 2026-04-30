<%-- 
    Document   : patientDashboard
    Created on : 25 Feb 2026, 6:19:30?pm
    Author     : LENOVO
--%>
<%@page import="com.mycompany.h_m_s.beans.Bean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.h_m_s.helpers.Helper"%>
<%@ include file="patientDashboard.html" %>
<div class="main-content">
    <div class="dashboard-container">

        <h3>Patients List</h3>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Contact No.</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Disease</th>
            </tr>
            <%
                Helper h = new Helper();
                ArrayList<Bean> list = h.getAllPatients();

                request.setAttribute("patientList", list);

                if (list != null) {
                    for (Bean p : list) {
            %>
            <tr>
                <td><%= p.getId()%></td>
                <td><%= p.getName()%></td>
                <td><%= p.getEmail()%></td>
                <td><%= p.getContact()%></td>
                <td><%= p.getAge()%></td>
                <td><%= p.getGender()%></td>
                <td><%= p.getDisease()%></td>
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

