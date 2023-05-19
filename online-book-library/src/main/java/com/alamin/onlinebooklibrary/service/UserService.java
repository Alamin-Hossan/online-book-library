package com.alamin.onlinebooklibrary.service;

import com.alamin.onlinebooklibrary.model.AuthenticationRequestModel;
import com.alamin.onlinebooklibrary.model.AuthenticationResponse;
import com.alamin.onlinebooklibrary.model.UserRequestModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    AuthenticationResponse registerUser(UserRequestModel userRequestModel);
    AuthenticationResponse login(AuthenticationRequestModel requestModel);
}
