package org.fis2021.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis2021.App;
import org.fis2021.model.Book;
import org.fis2021.services.BookService;

import java.io.IOException;

public class BookDetailsAdminController {
    public static String title, author, publishingHouse, genre, year, description;
    public static int count;
    private static ObjectRepository<Book> booksRepository = BookService.getBookRepository();
    private static Book book;
    
    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label houseLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Label genreLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label countLabel;
    @FXML
    private TextField countTextField;

    public void backButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("stock.fxml");
    }

    public static void setData(String title, String author, String publishingHouse, String genre, String year, String description, int count) {
        BookDetailsAdminController.title = title;
        BookDetailsAdminController.author = author;
        BookDetailsAdminController.publishingHouse = publishingHouse;
        BookDetailsAdminController.year = year;
        BookDetailsAdminController.genre = genre;
        BookDetailsAdminController.description = description;
        BookDetailsAdminController.count = count;
    }
    
    public void setLabelText() {
        titleLabel.setText(title);
        authorLabel.setText(author);
        houseLabel.setText(publishingHouse);
        yearLabel.setText(year);
        genreLabel.setText(genre);
        descriptionLabel.setText(description);
        countLabel.setText(String.valueOf(count));
    }

    public static void setRepo(ObjectRepository<Book> bookRepository, Book book) {
        BookDetailsAdminController.booksRepository = bookRepository;
        BookDetailsAdminController.book = book;
    }

    public void saveButtonOnAction() {
        if(!countTextField.getText().isEmpty()) {
            countLabel.setText(countTextField.getText());
            book.setCount(Integer.parseInt(countTextField.getText()));
            booksRepository.update(book);
        }
    }

    public void initialize() {
        setLabelText();
    }
}
