package org.fis2021.controllers;

import javafx.application.Platform;
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
import org.fis2021.services.CartService;
import org.fis2021.services.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HomePageCustomer {
    private static String username;
    private static ObjectRepository<Book> booksRepository = BookService.getBookRepository();
    private static ObservableList<Book> books;
    private static Book selectedBook;
    public static int id=0;

    @FXML
    private TableView<Book> booksTableView;
    @FXML
    private TableColumn<Book,String> titleColumn;
    @FXML
    private TableColumn<Book,String> authorColumn;
    @FXML
    private TableColumn<Book, String> houseColumn;
    @FXML
    private TableColumn<Book,String> yearColumn;

    @FXML
    public void initialize() {
        getAllBooks();
        id = OrderService.getId();

        Platform.runLater(() -> {
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
            yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            houseColumn.setCellValueFactory(new PropertyValueFactory<>("publishingHouse"));
            booksTableView.setItems(books);

            booksTableView.setRowFactory(e -> {
                TableRow<Book> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if(event.getClickCount() == 2 && !row.isEmpty()) {
                        selectedBook = booksTableView.getSelectionModel().getSelectedItem();
                        MakeOrderController.setId(id);
                        CartService.addCart(username,id,selectedBook.getTitle());
                    }
                });
                return row;
            });
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

    public static void setUsername(String username) {
        HomePageCustomer.username = username;
    }

    public void menuButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("Customer_menu.fxml");
    }
}
