package org.fis2021.exceptions;

public class EmptyFieldException extends Exception{
    public EmptyFieldException() {
        super("Please complete all fields!");
    }
}
