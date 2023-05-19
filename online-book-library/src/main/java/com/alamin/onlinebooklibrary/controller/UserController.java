package com.alamin.onlinebooklibrary.controller;


import com.alamin.onlinebooklibrary.model.AuthenticationRequestModel;
import com.alamin.onlinebooklibrary.model.UserRequestModel;
import com.alamin.onlinebooklibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRequestModel userRequestModel){
        return new ResponseEntity<>(userService.registerUser(userRequestModel), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequestModel requestModel){
        return new ResponseEntity<>(userService.login(requestModel),HttpStatus.OK);
    }
}
