package com.alamin.onlinebooklibrary.exceptions;

public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException(String message,Object msg){
        super(message + " " + msg);
    }
}
