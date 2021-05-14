package org.fis2021.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis2021.App;
import org.fis2021.model.Order;
import org.fis2021.services.OrderService;

import java.io.IOException;
import java.util.ArrayList;

public class OrderDetailsController {
    public static String name, email, phone, address, state;
    public static ArrayList<String> products;
    private static ObjectRepository<Order> ordersRepository = OrderService.getOrderRepository();
    private static Order order;
    ObservableList<String> list = FXCollections.observableArrayList("accepted","declined");

    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label productsLabel;
    @FXML
    private ComboBox<String> stateComboBox;
    @FXML
    private Button saveButton;

    public static void setData(String name, String email, String phone, String address, ArrayList<String> products, String state) {
       OrderDetailsController.name = name;
       OrderDetailsController.email = email;
       OrderDetailsController.phone = phone;
       OrderDetailsController.address = address;
       OrderDetailsController.products = products;
       OrderDetailsController.state = state;
    }

    public void setLabelText() {
        nameLabel.setText(name);
        emailLabel.setText(email);
        phoneLabel.setText(phone);
        addressLabel.setText(address);
        productsLabel.setText(products.toString());
        stateComboBox.setPromptText(state);
    }

    @FXML
    public void initialize() {
        stateComboBox.setItems(list);
        setLabelText();
    }

    public void backButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("adminHomePage.fxml");
    }

    public static void setRepo(ObjectRepository<Order> ordersRepository, Order order) {
        OrderDetailsController.ordersRepository = ordersRepository;
        OrderDetailsController.order = order;
    }

    public void saveButtonOnAction() {
        if(!stateComboBox.getSelectionModel().isEmpty()) {
            order.setState(stateComboBox.getValue());
            ordersRepository.update(order);
        }
    }
}
