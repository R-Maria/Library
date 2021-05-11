package org.fis2021.model;

import org.dizitart.no2.objects.Id;

public class Book {
    @Id
    private String title;
    private String author;
    private String publishingHouse;
    private String genre;
    private String year;
    private String description;
    private String imageURL;
    private int count;

    public Book(String title, String author, String publishingHouse, String genre, String year, String description, String imageURL, int count) {
        this.title = title;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.imageURL = imageURL;
        this.count = count;
    }

    public Book() {

    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        if (title != null ? !title.equals(book.title) : book.title != null)
            return false;
        if (author != null ? !author.equals(book.author) : book.author != null)
            return false;
        if (year != null ? !year.equals(book.year) : book.year != null)
            return false;
        return publishingHouse != null ? publishingHouse.equals(book.publishingHouse) : book.publishingHouse == null;
    }

    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publishingHouse != null ? publishingHouse.hashCode() : 0);
        return result;
    }

}
