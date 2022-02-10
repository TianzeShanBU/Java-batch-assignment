package com.example.hw3.exception;

public class CourseNotFoundException extends AccountException {
    public CourseNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
