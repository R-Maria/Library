package org.fis2021.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis2021.App;
import org.fis2021.model.Book;
import org.fis2021.services.BookService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class StockController {
    private static ObjectRepository<Book> booksRepository = BookService.getBookRepository();
    private static ObservableList<Book> books;
    private static Book selectedBook;

    @FXML
    private TableView<Book> stockTableView;
    @FXML
    private TableColumn<Book,String> titleColumn;
    @FXML
    private TableColumn<Book,String> authorColumn;
    @FXML
    private TableColumn<Book,String> houseColumn;
    @FXML
    private TableColumn<Book,String> yearColumn;
    @FXML
    private TableColumn<Book,String> countColumn;

    public void seeOrdersButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("adminHomePage.fxml");
    }

    public void addStockButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("addBook.fxml");
    }

    public void logOutButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("login.fxml");
    }

    public void initialize() {
        getAllBooks();
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        houseColumn.setCellValueFactory(new PropertyValueFactory<>("publishingHouse"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        stockTableView.setItems(books);

        stockTableView.setRowFactory(e -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && !row.isEmpty()) {
                    selectedBook = stockTableView.getSelectionModel().getSelectedItem();
                    BookDetailsAdminController.setData(selectedBook.getTitle(),selectedBook.getAuthor(),selectedBook.getPublishingHouse(),selectedBook.getGenre(),
                            selectedBook.getYear(),selectedBook.getDescription(),selectedBook.getCount());
                    BookDetailsAdminController.setRepo(booksRepository,selectedBook);
                    try {
                        App a = new App();
                        a.changeScene("bookDetailsAdmin.fxml");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    public static void getAllBooks() {
        booksRepository = BookService.getBookRepository();
        Cursor<Book> cursor = booksRepository.find();
        ArrayList<Book> list = new ArrayList<>();
        for(Book b : cursor) {
            list.add(b);
        }
        Collections.reverse(list);
        books = FXCollections.observableArrayList(list);
    }
}
