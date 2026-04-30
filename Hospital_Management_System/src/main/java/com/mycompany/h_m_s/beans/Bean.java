package com.mycompany.h_m_s.beans;

public class Bean {
    
    private int id;
    private String name;
    private String email;
    private String contact;
    private String password;
    private String role;
    
    private String specialization; 
    private String availabledays; 
    private String availabletime;
    
    private int age; 
    private String gender; 
    private String disease;
    
    public Bean() {
    }
    
    public Bean(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public Bean(int id, String name, String email, String contact, String specialization, String availabledays, String availabletime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.specialization = specialization;
        this.availabledays = availabledays;
        this.availabletime = availabletime;
    }
        
    public Bean(int id, String name, String email, String contact, int age, String gender, String disease) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    public String getAvailabledays() {
        return availabledays;
    }

    public void setAvailabledays(String availabledays) {
        this.availabledays = availabledays;
    }
    
    public String getAvailabletime() {
        return availabletime;
    }

    public void setAvailabletime(String availabletime) {
        this.availabletime = availabletime;
    }
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
    

