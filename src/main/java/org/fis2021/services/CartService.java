package org.fis2021.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis2021.model.Book;
import org.fis2021.model.Cart;

import static org.fis2021.services.FileSystemService.getPathToFile;

public class CartService {
    private static ObjectRepository<Cart> cartRepository;
    public static Nitrite database;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("libraryCart.db").toFile())
                .openOrCreate("test","test");
        cartRepository = database.getRepository(Cart.class);
    }

    public static void addCart(String username, int id, String book) {
        cartRepository.insert(new Cart(username,id,book));
    }

    public static ObjectRepository<Cart> getCartRepository() {
        return cartRepository;
    }
}
