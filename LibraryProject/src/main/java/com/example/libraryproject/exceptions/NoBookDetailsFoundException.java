package com.example.libraryproject.exceptions;

public class NoBookDetailsFoundException extends RuntimeException{

    public  NoBookDetailsFoundException(String message) {
        super(message);
    }
}
