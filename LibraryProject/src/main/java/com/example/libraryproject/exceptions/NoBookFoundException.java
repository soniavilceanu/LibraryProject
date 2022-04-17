package com.example.libraryproject.exceptions;

public class NoBookFoundException extends RuntimeException{

    public  NoBookFoundException(String message) {
        super(message);
    }
}
