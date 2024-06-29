/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 6/8/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Simple Address Book - Contact information
Description: Address book in JavaFX that allows a user to create new contact, update that contact, or delete that contact.
 */


import java.io.*;

// Contact class stores contact information 
class Contact implements Serializable {

    // Initialize variables
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String phone;
    private String email;
    private String notes;

    // Constructor
    public Contact(String firstName, String lastName, String street, String city, String state, String phone, String email, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
    }

    // Setter and Getters for each variable
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Simple toString that formats the contact name in the listView
    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }
}
