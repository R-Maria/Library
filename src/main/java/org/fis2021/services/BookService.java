package org.fis2021.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis2021.exceptions.*;
import org.fis2021.model.Book;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static org.fis2021.services.FileSystemService.getPathToFile;

public class BookService {
    private static ObjectRepository<Book> bookRepository;
    public static Nitrite database;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("libraryBooks.db").toFile())
                .openOrCreate("test","test");
        bookRepository = database.getRepository(Book.class);
    }

    public static void addUser(String title, String author, String publishingHouse, String genre, String year, String description, String imageURL, int count) throws BookAlreadyAdded {
        checkUserDoesNotAlreadyExist(title, author, publishingHouse, year);
        bookRepository.insert(new Book(title, author, publishingHouse, genre, year, description, imageURL, count));
    }

    public static void checkUserDoesNotAlreadyExist(String title, String author, String publishingHouse, String year) throws BookAlreadyAdded {
        for (Book book : bookRepository.find()) {
            if (Objects.equals(title, book.getTitle()) && Objects.equals(author, book.getAuthor()) && Objects.equals(publishingHouse, book.getPublishingHouse())
                    && Objects.equals(year, book.getYear())) {
                throw new BookAlreadyAdded(title);
            }
        }
    }

    public static void checkFieldsAddBook(String title, String author, String publishingHouse, String genre, String year, String description, String imageURL, String count) throws EmptyFieldException {
        if (title.isEmpty() || author.isEmpty() || publishingHouse.isEmpty() || genre.isEmpty() || year.isEmpty() || description.isEmpty() || imageURL.isEmpty() || count.isEmpty())
            throw new EmptyFieldException();
    }

    public static void checkCount(String number) throws WrongCountException {
        boolean test = true;
        try {
            int n = Integer.parseInt(number);
            if(n<0)
                throw new WrongCountException();
        } catch (NumberFormatException e) {
            test = false;
        }
        if(!test) {
            throw new WrongCountException();
        }
    }

    public static void checkYear(String number) throws WrongYearException {
        boolean test = true;
        try {
            int n = Integer.parseInt(number);
            if(n<0 || n>Calendar.getInstance().get(Calendar.YEAR))
                throw new WrongYearException();
        } catch (NumberFormatException e) {
            test = false;
        }
        if(!test) {
            throw new WrongYearException();
        }
    }

    public static ObjectRepository<Book> getBookRepository() {
        return bookRepository;
    }

    public static Nitrite getDatabase() {
        return database;
    }

    public static List<Book> getAllBooks() {
        return bookRepository.find().toList();
    }
}
