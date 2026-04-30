/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hms;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.util.Scanner;


/**
 *
 * @author LENOVO
 */
public class HospitalManagementSystem {

    public static void main(String[] args) {
    //System.out.println("Hello World!");
    
        String url = "jdbc:mysql://localhost:3306/hospitalDB";
        String username = "root";
        String password = "Nirali155";
  
        Connection con;
        Scanner sc = new Scanner(System.in);
        
        try {
          
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, username, password);
             
            System.out.println("Database Connected");
            
            Statement st = con.createStatement();
            
            String query = "CREATE TABLE IF NOT EXISTS Patients" +
                    "(patient_id INT AUTO_INCREMENT PRIMARY KEY,"  +
                    "name VARCHAR(50)," +
                    "age INT," +
                    "gender VARCHAR(10)," +
                    "contact VARCHAR(15))";
            
            st.execute(query);
            
            query = "CREATE TABLE IF NOT EXISTS Doctors" +
                    "(doctor_id INT AUTO_INCREMENT PRIMARY KEY,"  +
                    "name VARCHAR(50)," +
                    "specialization VARCHAR(50))"; 
                  
            st.execute(query);
 
            query = "CREATE TABLE IF NOT EXISTS Medicines" +
                    "(medicine_id INT AUTO_INCREMENT PRIMARY KEY,"  +
                    "name VARCHAR(50)," +
                    "price INT," +
                    "quantity INT)"; 
                  
            st.execute(query);
            
            int choice;
            
            do {
                System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
                
                System.out.println("1. Patient Module");
                System.out.println("2. Doctor Module");
                System.out.println("3. Medicine Module");
                System.out.println("4. Exit");
                
                System.out.print("Enter choice: ");

                choice = sc.nextInt();
                sc.nextLine();
             
                switch (choice) {
                    
                    case 1:
                        
                        PatientDB.patientMenu(con);
                        break;

                    case 2:
                        
                        DoctorDB.doctorMenu(con);
                        break;

                    case 3:
                        
                        MedicineDB.medicineMenu(con);
                        break;

                    case 4:
                        
                        System.out.println("Thank You!");
                        break;

                    default:
                        
                        System.out.println("Invalid Choice");
                }

            } while (choice != 4);
            
        } catch (SQLException | ClassNotFoundException e) {
            
            System.out.println("Driver not found");          
        } 
        
    }
} 
