package org.fis2021.model;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private ArrayList<Book> books;
    private String state;
    public Order(String name,String email,String phoneNumber,String address,ArrayList<Book> books,String state){
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.books=books;
        this.state=state;
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

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return name.equals(order.name) && email.equals(order.email) && phoneNumber.equals(order.phoneNumber) && address.equals(order.address) && books.equals(order.books) && state.equals(order.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber, address, books, state);
    }
}
