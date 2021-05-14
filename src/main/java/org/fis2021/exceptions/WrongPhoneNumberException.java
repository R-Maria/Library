package org.fis2021.exceptions;

public class WrongPhoneNumberException extends Exception{

    public WrongPhoneNumberException() {
        super("The phone number format is invalid!");
    }
}
