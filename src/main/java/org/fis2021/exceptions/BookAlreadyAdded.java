package org.fis2021.exceptions;

public class BookAlreadyAdded extends Exception{
    public BookAlreadyAdded(String title) {
        super(String.format("The book \"%s\" already exists!", title));
    }
}
