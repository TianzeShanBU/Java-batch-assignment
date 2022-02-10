package com.example.hw3.exception;

public class AccountException extends RuntimeException{
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public AccountException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public AccountException() {
        super();
    }
}
