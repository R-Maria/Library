package org.fis2021.exceptions;

public class UsernameAlreadyExistsException extends Exception {

    public UsernameAlreadyExistsException(String username) {
        super(String.format("The username %s already exists!", username));
    }
}
