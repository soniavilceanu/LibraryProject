package com.example.libraryproject.exceptions;

public class NoAuthorFoundException extends RuntimeException{

    public  NoAuthorFoundException(String message) {
        super(message);
    }
}
