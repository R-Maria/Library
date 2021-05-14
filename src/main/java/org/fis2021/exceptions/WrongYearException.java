package org.fis2021.exceptions;

public class WrongYearException extends Exception{
    public WrongYearException() {
        super("The year is invalid!");
    }
}
