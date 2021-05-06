package org.fis2021.exceptions;

public class PasswordIncorrectException extends Exception{
    private String password;

    public PasswordIncorrectException(String password) {
        super("Username, password or role entered is incorrect!");
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
