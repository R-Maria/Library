package org.fis2021.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis2021.App;
import org.fis2021.model.Book;
import org.fis2021.model.Order;

import javafx.scene.control.TableView;
import org.fis2021.services.CartService;
import org.fis2021.services.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class AdminHomePageController {
    private static String name;
    private static ObjectRepository<Order> ordersRepository = OrderService.getOrderRepository();
    private static ObservableList<Order> orders;
    private static Order selectedOrder;

    @FXML
    private TableView<Order> ordersTableView;
    @FXML
    private TableColumn<Order,String> nameColumn;
    @FXML
    private TableColumn<Order, ArrayList<String>> productsColumn;
    @FXML
    private TableColumn<Order,String> stateColumn;

    public void initialize() {
            getAllOrders();

            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            productsColumn.setCellValueFactory(new PropertyValueFactory<>("books"));
            stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
            ordersTableView.setItems(orders);

            ordersTableView.setRowFactory(e -> {
                TableRow<Order> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && !row.isEmpty()) {
                        selectedOrder = ordersTableView.getSelectionModel().getSelectedItem();
                        OrderDetailsController.setData(selectedOrder.getName(), selectedOrder.getEmail(), selectedOrder.getPhoneNumber(), selectedOrder.getAddress(), selectedOrder.getBooks(), selectedOrder.getState());
                        OrderDetailsController.setRepo(ordersRepository,selectedOrder);
                        try {
                            App a = new App();
                            a.changeScene("orderDetails.fxml");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });
                return row;
            });
    }

    public static void getAllOrders() {
        ordersRepository = OrderService.getOrderRepository();
        Cursor<Order> cursor = ordersRepository.find();
        ArrayList<Order> list = new ArrayList<>();
        for(Order b : cursor) {
            list.add(b);
        }
        Collections.reverse(list);
        orders = FXCollections.observableArrayList(list);
    }

    public void addStockButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("addBook.fxml");
    }

    public void logOutButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("login.fxml");
    }
}
