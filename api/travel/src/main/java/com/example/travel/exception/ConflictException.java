package com.example.travel.exception;

public class ConflictException extends Exception {
    public ConflictException() {
        super();
    }

    public ConflictException(String message) {
        super(message);
    }
}
