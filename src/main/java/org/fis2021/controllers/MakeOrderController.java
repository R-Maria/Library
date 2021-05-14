package org.fis2021.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Cursor;

import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.fis2021.App;
import org.fis2021.exceptions.EmptyFieldException;
import org.fis2021.exceptions.WrongEmailException;
import org.fis2021.exceptions.WrongPhoneNumberException;
import org.fis2021.model.Cart;
import org.fis2021.services.CartService;
import org.fis2021.services.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class MakeOrderController {
    private static String username;
    private static int id;
    private static ObjectRepository<Cart> cartRepository = CartService.getCartRepository();
    private static ObservableList<Cart> books;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextArea addressTextArea;
    @FXML
    private TableView<Cart> productsTableView;
    @FXML
    private TableColumn<Cart,String> productsTableColumn;
    @FXML
    private Label addBookMessageLabel;

    @FXML
    public void initialize() {
        getAllBooks();

        Platform.runLater(() ->{
           productsTableColumn.setCellValueFactory(new PropertyValueFactory<>("book"));
           productsTableView.setItems(books);
        });
    }

    public static void getAllBooks() {
        cartRepository = CartService.getCartRepository();
        Cursor<Cart> cursor = cartRepository.find(eq("id",id));
        ArrayList<Cart> list = new ArrayList<>();
        for(Cart c : cursor) {
            list.add(c);
        }
        Collections.reverse(list);
        books = FXCollections.observableArrayList(list);
    }

    public static void setUsername(String username) {
        MakeOrderController.username = username;
    }

    public static void setId(int id) {
        MakeOrderController.id = id;
    }

    public void orderButtonOnAction() {
        try {
            ArrayList<String> titles = new ArrayList<>();
            for(int i=0; i<productsTableView.getItems().size(); i++) {
                titles.add(productsTableView.getItems().get(i).getBook());
            }
            addBookMessageLabel.setText("Order placed successfully!");
            OrderService.checkFields(nameTextField.getText(),emailTextField.getText(),phoneTextField.getText(),addressTextArea.getText(),titles);
            OrderService.checkEmail(emailTextField.getText());
            OrderService.checkPhoneNumber(phoneTextField.getText());
            String uniqueId = NitriteId.newId().toString();
            OrderService.addOrder(uniqueId,nameTextField.getText(), emailTextField.getText(),phoneTextField.getText(), addressTextArea.getText(),titles);
            cartRepository.remove(ObjectFilters.ALL);
        } catch (EmptyFieldException | WrongEmailException | WrongPhoneNumberException e) {
            addBookMessageLabel.setText(e.getMessage());
        }
    }

    public void backButtonOnAction() throws IOException {
        App a = new App();
        a.changeScene("HomePage_Customer.fxml");
    }

}
