package com.alamin.onlinebooklibrary.exceptions;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String message){
        super(message);
    }
}
