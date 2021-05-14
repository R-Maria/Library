package org.fis2021.model;

public class Cart {
    private String username;
    private int id=0;
    private String book;

    public Cart(String username, int id, String book) {
        this.username = username;
        this.id = id;
        this.book = book;
    }

    public Cart() {

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getId() {
        return id;
    }
    public String getBook() {
        return book;
    }
    public void setBooks(String book) {
        this.book = book;
    }
}
