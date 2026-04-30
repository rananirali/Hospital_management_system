package com.mycompany.h_m_s.beans;

import jakarta.persistence.*;

@Entity
@Table(name = "Doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String contact;
    private String password;
    private String specialization;
    private String availabledays;
    private String availabletime;

    public Doctor() {
    }

    public Doctor(int id, String name, String email, String contact, String specialization, String availabledays, String availabletime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.specialization = specialization;
        this.availabledays = availabledays;
        this.availabletime = availabletime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
