package com.example.libraryproject.exceptions;

public class NoLibraryFoundException extends RuntimeException{

    public  NoLibraryFoundException(String message) {
        super(message);
    }
}
