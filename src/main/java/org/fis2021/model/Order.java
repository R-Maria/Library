package org.fis2021.model;

import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private ArrayList<String> books;
    private String state;

    public Order(String name, String email, String phoneNumber, String address, ArrayList<String> books) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.books = books;
        this.state = "to be decided";
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

}
