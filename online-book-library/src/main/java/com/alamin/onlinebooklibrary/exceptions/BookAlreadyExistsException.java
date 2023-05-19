package com.alamin.onlinebooklibrary.exceptions;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String message, String meg2){
        super(message+meg2);
    }
}
