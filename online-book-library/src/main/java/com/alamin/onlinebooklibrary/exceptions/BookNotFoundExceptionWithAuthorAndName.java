package com.alamin.onlinebooklibrary.exceptions;

public class BookNotFoundExceptionWithAuthorAndName extends RuntimeException{
    public BookNotFoundExceptionWithAuthorAndName(String meg1, String meg2, String meg3, String meg4){
        super(meg1+meg2+meg3+meg4);
    }
}
