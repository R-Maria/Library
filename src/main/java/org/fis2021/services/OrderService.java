package org.fis2021.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis2021.exceptions.EmptyFieldException;
import org.fis2021.exceptions.WrongEmailException;
import org.fis2021.exceptions.WrongPhoneNumberException;
import org.fis2021.model.Book;
import org.fis2021.model.Order;

import java.util.ArrayList;

import static org.fis2021.services.FileSystemService.getPathToFile;

public class OrderService {
    private static ObjectRepository<Order> orderRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("libraryOrders.db").toFile())
                .openOrCreate("test","test");
        orderRepository = database.getRepository(Order.class);
    }

    public static void checkPhoneNumber(String number) throws WrongPhoneNumberException {
        if (!number.matches("^[0]+[7]+\\d{8}"))
            throw new WrongPhoneNumberException();
    }

    public static void checkEmail(String email) throws WrongEmailException {
        if(!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"))
            throw new WrongEmailException();
    }

    public static void checkFields(String name, String email, String phoneNumber, String address, String state, ArrayList<Book> books) throws EmptyFieldException {
        if(name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || books.isEmpty())
            throw new EmptyFieldException();
    }
}
