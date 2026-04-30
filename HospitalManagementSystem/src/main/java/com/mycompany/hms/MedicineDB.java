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
public class MedicineDB {
    
    static Scanner sc = new Scanner(System.in);

    public static void insertMedicine(Connection con) {

        try {
            
            System.out.print("Medicine Name: ");
            String name = sc.nextLine();

            System.out.print("Price: ");
            int price = sc.nextInt();

            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();

            String prep_sql = "INSERT INTO Medicines(name,price,quantity) VALUES (?,?,?)";
            PreparedStatement prep_st = con.prepareStatement(prep_sql);
            
            prep_st.setString(1, name);
            prep_st.setInt(2, price);
            prep_st.setInt(3, qty);
            
            prep_st.executeUpdate();

            System.out.println("Medicine Added");

        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }

    public static void viewMedicines(Connection con) {

        try {
            
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM Medicines");

            System.out.println("\nID  Name  Price  Quantity");

            while (rs.next()) {
                
                System.out.println(
                    rs.getInt(1) + " " +
                    rs.getString(2) + " " +
                    rs.getInt(3) + " " +
                    rs.getInt(4)
                );
            }

        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }

    public static void updateMedicine(Connection con) {

        try {
            
            System.out.print("Enter Medicine ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("1. Update Name");
            System.out.println("2. Update Price");
            System.out.println("3. Update Quantity");
            
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            String sql;
            PreparedStatement prep_st;

            switch (ch) {

                case 1:
                    
                    System.out.print("New Name: ");
                    String name = sc.nextLine();
                    
                    sql = "UPDATE Medicines SET name=? WHERE medicine_id=?";
                    prep_st = con.prepareStatement(sql);
                    
                    prep_st.setString(1, name);
                    prep_st.setInt(2, id);
                    break;

                case 2:
                    
                    System.out.print("New Price: ");
                    int price = sc.nextInt();
                    
                    sql = "UPDATE Medicines SET price=? WHERE medicine_id=?";
                    prep_st = con.prepareStatement(sql);
                    
                    prep_st.setInt(1, price);
                    prep_st.setInt(2, id);
                    break;

                case 3:
                    
                    System.out.print("New Quantity: ");
                    int qty = sc.nextInt();
                    
                    sql = "UPDATE Medicines SET quantity=? WHERE medicine_id=?";
                    prep_st = con.prepareStatement(sql);
                    
                    prep_st.setInt(1, qty);
                    prep_st.setInt(2, id);
                    break;

                default:
                    
                    System.out.println("Invalid Choice");
                    return;
            }

            prep_st.executeUpdate();
            
            System.out.println("Medicine Updated");

        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }

    public static void deleteMedicine(Connection con) {

        try {
            
            System.out.print("Enter Medicine ID to Delete: ");
            int id = sc.nextInt();

            String prep_sql = "DELETE FROM Medicines WHERE medicine_id=?";
            PreparedStatement prep_st = con.prepareStatement(prep_sql);
            
            prep_st.setInt(1, id);
            
            prep_st.executeUpdate();

            System.out.println("Medicine Deleted");

        } catch (SQLException e) {
            
            System.out.println(e);
        }
    }

    public static void medicineMenu(Connection con) {

        int choice;

        do {
            
            System.out.println("\n--- MEDICINE MENU ---");
            
            System.out.println("1. Insert Medicine");
            System.out.println("2. View Medicines");
            System.out.println("3. Update Medicine");
            System.out.println("4. Delete Medicine");
            System.out.println("5. Exit");
            
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                
                case 1: 
                        insertMedicine(con); 
                        break;
                        
                case 2: 
                        viewMedicines(con); 
                        break;
                        
                case 3: 
                        updateMedicine(con); 
                        break;
                        
                case 4: 
                        deleteMedicine(con); 
                        break;
                        
                case 5: 
                        System.out.println("Exit Medicine Module"); 
                        break;
                        
                default: 
                        System.out.println("Invalid Choice");
            }

        } while (choice != 5);
    }
}
    
