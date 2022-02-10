package com.example.hw3.exception;

public class StudentNotFoundException extends AccountException {
    public StudentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
