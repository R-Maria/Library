package org.fis2021.exceptions;

public class WrongCountException extends Exception{
    public WrongCountException() {
        super("The count is invalid!");
    }
}
