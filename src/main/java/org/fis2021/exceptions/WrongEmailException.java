package org.fis2021.exceptions;

public class WrongEmailException extends Exception{

    public WrongEmailException() {
        super("Email format is invalid!");
    }
}
