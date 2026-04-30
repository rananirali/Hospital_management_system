/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hms;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author LENOVO
 */
public class PatientDB {

    static Scanner sc = new Scanner(System.in);

    public static void insertPatient(Connection con) {
        
        try {
            
            System.out.print("Name: ");
            String name = sc.nextLine();
            
            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Gender: ");
            String gender = sc.nextLine();
            
            System.out.print("Contact: ");
            String contact = sc.nextLine();
            
            String prep_sql = "INSERT INTO Patients(name,age,gender,contact) VALUES (?,?,?,?)";
            PreparedStatement prep_st = con.prepareStatement(prep_sql);
             
            prep_st.setString(1, name);
            prep_st.setInt(2, age);
            prep_st.setString(3, gender);
            prep_st.setString(4, contact);
            
            prep_st.executeUpdate();
            
            System.out.println("Patient Added");
            
        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }

    public static void viewPatients(Connection con) {
        
        try {
            
            Statement st = con.createStatement();
            
            String QUERY = "SELECT * FROM Patients";
            ResultSet rs = st.executeQuery(QUERY);
            
            System.out.println("\nID  Name  Age  Gender  Contact");
            
            while (rs.next()) {
                
                System.out.println(
                        rs.getInt(1)+" "+
                        rs.getString(2)+" "+
                        rs.getInt(3)+" "+
                        rs.getString(4)+" "+
                        rs.getString(5)
                );
            }
            
        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }
    
    public static void updatePatient(Connection con) {

        try {
            
            System.out.print("Enter Patient ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            
            System.out.println("1. Update Name");
            System.out.println("2. Update Age");
            System.out.println("3. Update Gender");
            System.out.println("4. Update Contact");
            
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            
            String sql;
            PreparedStatement prep_st;
            
            switch (ch) {
                
                case 1:
                    
                    System.out.print("New Name: ");
                    String name = sc.nextLine();
                    
                    sql = "UPDATE Patients SET name=? WHERE patient_id=?";
                    prep_st = con.prepareStatement(sql);
                    
                    prep_st.setString(1, name);
                    prep_st.setInt(2, id);
                    break;
                    
                case 2:
                    
                    System.out.print("New Age: ");
                    int age = sc.nextInt();
                    
                    sql = "UPDATE Patients SET age=? WHERE patient_id=?";
                    prep_st = con.prepareStatement(sql);
                    
                    prep_st.setInt(1, age);
                    prep_st.setInt(2, id);
                    break;
                    
                case 3:
                    
                    System.out.print("New Gender: ");
                    String gender = sc.nextLine();
                    
                    sql = "UPDATE Patients SET gender=? WHERE patient_id=?";
                    prep_st = con.prepareStatement(sql);
                    
                    prep_st.setString(1, gender);
                    prep_st.setInt(2, id);
                    break;
                    
                case 4:
                    
                    System.out.print("New Contact: ");
                    String contact = sc.nextLine();
                    
                    sql = "UPDATE Patients SET contact=? WHERE patient_id=?";
                    prep_st = con.prepareStatement(sql);
                    
                    prep_st.setString(1, contact);
                    prep_st.setInt(2, id);
                    break;
                    
                default:
                    
                    System.out.println("Invalid Choice");
                    return;
            }
            
            prep_st.executeUpdate();
            
            System.out.println("Patient Updated");
            
        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }

    public static void deletePatient(Connection con) {

        try {
            
            System.out.print("Enter Patient ID to Delete: ");
            int id = sc.nextInt();
            
            String prep_sql =  "DELETE FROM Patients WHERE patient_id=?";
            PreparedStatement prep_st = con.prepareStatement(prep_sql);
            
            prep_st.setInt(1, id);
            
            prep_st.executeUpdate();
            
            System.out.println("Patient Deleted");
            
        } catch (SQLException e) {
            
           System.out.println(e);
        }
    }

    public static void patientMenu(Connection con) {

        int choice;
        
        do {
            
            System.out.println("\n--- PATIENT MENU ---");
            
            System.out.println("1. Insert Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Exit");
            
            System.out.print("Enter choice: ");

            choice = sc.nextInt(); 
            sc.nextLine();

            switch (choice) {
                
                case 1: 
                        insertPatient(con); 
                        break;
                        
                case 2: 
                        viewPatients(con); 
                        break;
                        
                case 3: 
                        updatePatient(con); 
                        break;
                        
                case 4: 
                        deletePatient(con); 
                        break;
                        
                case 5: 
                        System.out.println("Exit Patient Module"); 
                        break;
                        
                default: 
                        System.out.println("Invalid Choice");
            }

        } while (choice != 5);  
    }  
}


     