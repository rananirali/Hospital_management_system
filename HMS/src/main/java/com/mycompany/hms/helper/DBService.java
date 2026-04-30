package com.mycompany.hms.helper;

import com.mycompany.hms.beans.Bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBService {
    
    private String url;
    private String username;
    private String password;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() {
        
        Connection con = null;
        
        try {
            
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);
        System.out.println("connection succssful");
        
        Statement st = con.createStatement();
            
                String query = "CREATE TABLE IF NOT EXISTS DoctorsData" +
                        "(doctor_id INT AUTO_INCREMENT PRIMARY KEY,"  +
                        "name VARCHAR(50)," +
                        "email VARCHAR(50)," +
                        "contact VARCHAR(15)," +
                        "password VARCHAR(50)," +
                        "specialization VARCHAR(50)," +
                        "availabledays VARCHAR(50)," +
                        "availabletime VARCHAR(50))";
            
                st.execute(query);
            
                query = "CREATE TABLE IF NOT EXISTS PatientsData" +
                        "(patient_id INT AUTO_INCREMENT PRIMARY KEY,"  +
                        "name VARCHAR(50)," +
                        "email VARCHAR(50)," +
                        "contact VARCHAR(15)," +
                        "password VARCHAR(50)," +
                        "age INT," +
                        "gender VARCHAR(10)," +
                        "disease VARCHAR(50))"; 
                  
                st.execute(query);
                
                query = "CREATE TABLE IF NOT EXISTS AdminsData" +
                        "(id INT AUTO_INCREMENT PRIMARY KEY,"  +
                        "name VARCHAR(50)," +
                        "email VARCHAR(50)," +
                        "contact VARCHAR(15)," +
                        "password VARCHAR(50))"; 
                  
                st.execute(query);
                
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
       
    } 
        
    public void insertUser(Connection con, Bean user) {
        
        try {
            
                PreparedStatement prep_st;
               
                if(user.getRole().equals("patient")) {
            
                    String prep_sql = "INSERT INTO PatientsData(name,email,contact,password) VALUES (?,?,?,?)";
                    prep_st = con.prepareStatement(prep_sql);
                 
                } else {
            
                    String prep_sql = "INSERT INTO DoctorsData(name,email,contact,password) VALUES (?,?,?,?)";
                    prep_st = con.prepareStatement(prep_sql);
                }
                
                prep_st.setString(1, user.getName());
                prep_st.setString(2, user.getEmail());
                prep_st.setString(3, user.getContact());
                prep_st.setString(4, user.getPassword());
            
                prep_st.executeUpdate();
            
                System.out.println("User Added");       
            
            } catch (SQLException e) {
            
                System.out.println(e);
            }      
    } 
    
    public String validateUser(Bean user) {
        
        Connection con = getConnection();
        
        if (con != null) {
            
            try {
                    
                String prep_sql1 = "SELECT * FROM PatientsData WHERE email = ? AND password = ?";
                PreparedStatement prep_st1 = con.prepareStatement(prep_sql1);
                
                prep_st1.setString(1, user.getEmail());
                prep_st1.setString(2, user.getPassword());
                System.out.println("Query : " + prep_st1.toString());
                ResultSet rs1 = prep_st1.executeQuery();

                if (rs1.next()) {
                    return "patient";  
                }
                
                String prep_sql2 = "SELECT * FROM DoctorsData WHERE email = ? AND password = ?";
                PreparedStatement prep_st2 = con.prepareStatement(prep_sql2);
                   
                prep_st2.setString(1, user.getEmail());
                prep_st2.setString(2, user.getPassword());
                System.out.println("Query : " + prep_st2.toString());
                ResultSet rs2 = prep_st2.executeQuery();
                
                if (rs2.next()) {
                    return "doctor";  
                }
                
                String prep_sql3 = "SELECT * FROM AdminsData WHERE email = ? AND password = ?";
                PreparedStatement prep_st3 = con.prepareStatement(prep_sql3);
                   
                prep_st3.setString(1, user.getEmail());
                prep_st3.setString(2, user.getPassword());
                System.out.println("Query : " + prep_st3.toString());
                ResultSet rs3 = prep_st3.executeQuery();
                
                if (rs3.next()) {
                    return "admin";  
                }
                     
            } catch (SQLException e) {
                
                System.out.println(e);
            }
        }
        return "invalid";
    }
    
    public ArrayList<Bean> getAllDoctors() {

        ArrayList<Bean> list = new ArrayList<>();
        Connection con = getConnection();

        if (con != null) {
            try {
                String prep_sql = "SELECT doctor_id, name, email, contact, specialization, availabledays, availabletime FROM DoctorsData";
                PreparedStatement prep_st = con.prepareStatement(prep_sql);
                System.out.println("Query : " + prep_st.toString());
                ResultSet rs = prep_st.executeQuery();

                while (rs.next()) {
                    Bean doctor = new Bean(
                            rs.getInt("doctor_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("contact"),
                            rs.getString("specialization"), 
                            rs.getString("availabledays"), 
                            rs.getString("availabletime")
                    );
                    list.add(doctor);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }
    
    public ArrayList<Bean> getAllPatients() {

        ArrayList<Bean> list = new ArrayList<>();
        Connection con = getConnection();

        if (con != null) {
            try {
                String prep_sql = "SELECT patient_id, name, email, contact, age, gender, disease FROM PatientsData";
                PreparedStatement prep_st = con.prepareStatement(prep_sql);
                System.out.println("Query : " + prep_st.toString());
                ResultSet rs = prep_st.executeQuery();

                while (rs.next()) {
                    Bean patient = new Bean(
                            rs.getInt("patient_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("contact"),
                            rs.getInt("age"), 
                            rs.getString("gender"), 
                            rs.getString("disease")
                    );
                    list.add(patient);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }
    
    public Bean fetchDoctor(int doctor_id) {
        
        Bean b = null;
        Connection con = getConnection();
        if (con != null) {
            try {
                
                PreparedStatement prep_st;
                String query = "SELECT * FROM DoctorsData WHERE doctor_id = ?";
                prep_st = con.prepareStatement(query);
                prep_st.setInt(1, doctor_id);
                System.out.println("Query : " + prep_st.toString());
                ResultSet rs = prep_st.executeQuery();
                if (rs.next()) {
               
                    b = new Bean(rs.getInt("doctor_id"), rs.getString("name"), rs.getString("email"), rs.getString("contact"), rs.getString("specialization"), rs.getString("availabledays"), rs.getString("availabletime"));
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return b;
    }
    
    public void editDoctor(Bean user) {
        Connection con = getConnection();
        if (con != null) {

            try {
                
                String prep_sql = "UPDATE DoctorsData SET name = ?, email = ?, contact= ?, specialization = ?, availabledays = ?, availabletime = ?  WHERE doctor_id = ?";
                PreparedStatement prep_st;
                prep_st = con.prepareStatement(prep_sql);

                prep_st.setString(1, user.getName());
                prep_st.setString(2, user.getEmail());
                prep_st.setString(3, user.getContact());
                prep_st.setString(4, user.getSpecialization());
                prep_st.setString(5, user.getAvailabledays());
                prep_st.setString(6, user.getAvailabletime());
                prep_st.setInt(7, user.getId());
                System.out.println("Query : " + prep_st.toString());
                prep_st.executeUpdate();
                prep_st.close();

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    public void deleteDoctor(int doctor_id) {
        Connection con = getConnection();
        if (con != null) {

            try {
                
                String prep_sql = "DELETE FROM DoctorsData WHERE doctor_id = ?";
                try (PreparedStatement prep_st = con.prepareStatement(prep_sql)) {
                    prep_st.setInt(1, doctor_id);
                    System.out.println("Query : " + prep_st.toString());
                    prep_st.executeUpdate();
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    public Bean fetchPatient(int patient_id) {
        
        Bean b = null;
        Connection con = getConnection();
        if (con != null) {
            try {
                
                PreparedStatement prep_st;
                String query = "SELECT * FROM PatientsData WHERE patient_id = ?";
                prep_st = con.prepareStatement(query);
                prep_st.setInt(1, patient_id);
                System.out.println("Query : " + prep_st.toString());
                ResultSet rs = prep_st.executeQuery();
                if (rs.next()) {
               
                    b = new Bean(rs.getInt("patient_id"), rs.getString("name"), rs.getString("email"), rs.getString("contact"), rs.getInt("age"), rs.getString("gender"), rs.getString("disease"));
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return b;
    } 
    
    public void editPatient(Bean user) {
        Connection con = getConnection();
        if (con != null) {

            try {
                
                String prep_sql = "UPDATE PatientsData SET name = ?, email = ?, contact= ?, age = ?, gender = ?, disease = ? WHERE patient_id = ?";
                PreparedStatement prep_st;
                prep_st = con.prepareStatement(prep_sql);

                prep_st.setString(1, user.getName());
                prep_st.setString(2, user.getEmail());
                prep_st.setString(3, user.getContact());
                prep_st.setInt(4, user.getAge());
                prep_st.setString(5, user.getGender());
                prep_st.setString(6, user.getDisease());
                prep_st.setInt(7, user.getId());
                System.out.println("Query : " + prep_st.toString());
                prep_st.executeUpdate();
                prep_st.close();

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    public void deletePatient(int patient_id) {
        Connection con = getConnection();
        if (con != null) {

            try {
                
                String prep_sql = "DELETE FROM PatientsData WHERE patient_id = ?";
                try (PreparedStatement prep_st = con.prepareStatement(prep_sql)) {
                    prep_st.setInt(1, patient_id);
                    System.out.println("Query : " + prep_st.toString());
                    prep_st.executeUpdate();
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
