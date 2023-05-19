package com.alamin.onlinebooklibrary.controller;

import com.alamin.onlinebooklibrary.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BookNotFoundException.class,
            RoleNotFoundException.class,
            UserAlreadyExistException.class,
            RegisterException.class,
            RoleExistsException.class,
            InvalidEmailException.class,
            InvalidPasswordException.class,
            BookNotFoundExceptionWithAuthorAndName.class,
            BookCreationException.class,
            BookAlreadyExistsException.class
    })
    public ResponseEntity<Object> returnNotFoundException(Exception ex) {
        if(ex instanceof BookNotFoundException) {
            // Some operation
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } else if(ex instanceof RegisterException) {
            // Some operation for candidate not found
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } else if(ex instanceof UserAlreadyExistException ){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);

        }else if(ex instanceof RoleExistsException){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }else if(ex instanceof RoleNotFoundException){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } else if (ex instanceof InvalidEmailException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);

        }else if (ex instanceof InvalidPasswordException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);

        }else if (ex instanceof BookNotFoundExceptionWithAuthorAndName) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }else if (ex instanceof BookCreationException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);

        } else if (ex instanceof BookAlreadyExistsException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);

        }else {
            // Some other operation
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
