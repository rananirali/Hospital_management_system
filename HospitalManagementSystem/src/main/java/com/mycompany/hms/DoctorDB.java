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
public class DoctorDB {
    
    static Scanner sc = new Scanner(System.in);

    public static void insertDoctor(Connection con) {
        
        try {
             
            System.out.print("Doctor Name: ");
            String name = sc.nextLine();

            System.out.print("specialization: ");
            String specialization = sc.nextLine();

            String prep_sql = "INSERT INTO Doctors(name, specialization) VALUES (?, ?)";
            PreparedStatement prep_st = con.prepareStatement(prep_sql);
            
            prep_st.setString(1, name);
            prep_st.setString(2, specialization);
            
            prep_st.executeUpdate();

            System.out.println("Doctor Added");

        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }

    public static void viewDoctors(Connection con) {
        
        try {
            
            Statement st = con.createStatement();
            
            String QUERY = "SELECT * FROM Doctors";
            ResultSet rs = st.executeQuery(QUERY);

            System.out.println("\nID  Name  specialization");

            while (rs.next()) {
                
                System.out.println(
                    rs.getInt(1) + " " +
                    rs.getString(2) + " " +
                    rs.getString(3)
                );
            }

        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }

    public static void updateDoctor(Connection con) {
        
        try {
            
            System.out.print("Enter Doctor ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("1. Update Name");
            System.out.println("2. Update specialization");
            
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            String sql;
            PreparedStatement prep_st;

            switch (ch) {

                case 1:
                    
                    System.out.print("New Name: ");
                    String name = sc.nextLine();
                    
                    sql = "UPDATE Doctors SET name=? WHERE doctor_id=?";
                    prep_st = con.prepareStatement(sql);
                    
                    prep_st.setString(1, name);
                    prep_st.setInt(2, id);
                    break;

                case 2:
                    
                    System.out.print("New specialization: ");
                    String specialization = sc.nextLine();
                    
                    sql = "UPDATE Doctors SET specialization=? WHERE doctor_id=?";
                    prep_st = con.prepareStatement(sql);
                    
                    prep_st.setString(1, specialization);
                    prep_st.setInt(2, id);
                    break;

                default:
                    
                    System.out.println("Invalid Choice");
                    return;
            }

            prep_st.executeUpdate();
            
            System.out.println("Doctor Updated");

        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }

    public static void deleteDoctor(Connection con) {
        
        try {
            
            System.out.print("Enter Doctor ID to Delete: ");
            int id = sc.nextInt();

            String prep_sql = "DELETE FROM Doctors WHERE doctor_id=?";
            PreparedStatement prep_st = con.prepareStatement(prep_sql);
            
            prep_st.setInt(1, id);
            
            prep_st.executeUpdate();

            System.out.println("Doctor Deleted");

        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }
    
    public static void doctorMenu(Connection con) {

        int choice;

        do {
            
            System.out.println("\n--- DOCTOR MENU ---");
            
            System.out.println("1. Insert Doctor");
            System.out.println("2. View Doctors");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Exit");
            
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                
                case 1:
                        insertDoctor(con); 
                        break;
                        
                case 2: 
                        viewDoctors(con); 
                        break;
                        
                case 3:
                        updateDoctor(con); 
                        break;
                        
                case 4: 
                        deleteDoctor(con); 
                        break;
                        
                case 5: 
                        System.out.println("Exit Doctor Module"); 
                        break;
                        
                default: 
                        System.out.println("Invalid Choice");
            }

        } while (choice != 5);
    }
}