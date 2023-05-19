package com.alamin.onlinebooklibrary.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message,Object msg){
        super(message + " " + msg);
    }
}
