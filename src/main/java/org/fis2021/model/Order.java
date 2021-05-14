package org.fis2021.model;

import org.dizitart.no2.objects.Id;

import java.util.ArrayList;

public class Order {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private ArrayList<String> books;
    private String state;

    public Order(String id, String name, String email, String phoneNumber, String address, ArrayList<String> books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.books = books;
        this.state = "pending";
    }

    public Order() {

    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<String> books) {
        this.books = books;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
