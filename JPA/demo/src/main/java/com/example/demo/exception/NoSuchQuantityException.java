package com.example.demo.exception;

public class NoSuchQuantityException extends RuntimeException {
    public NoSuchQuantityException() {
    }

    public NoSuchQuantityException(String message) {
        super(message);
    }
}
