package com.alamin.onlinebooklibrary.service.impl;

import com.alamin.onlinebooklibrary.entity.Role;
import com.alamin.onlinebooklibrary.entity.User;


import com.alamin.onlinebooklibrary.exceptions.*;
import com.alamin.onlinebooklibrary.model.AuthenticationRequestModel;
import com.alamin.onlinebooklibrary.model.AuthenticationResponse;
import com.alamin.onlinebooklibrary.model.UserRequestModel;
import com.alamin.onlinebooklibrary.repository.UserRepository;
import com.alamin.onlinebooklibrary.service.RoleService;
import com.alamin.onlinebooklibrary.service.UserService;
import com.alamin.onlinebooklibrary.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    @Override
    public AuthenticationResponse registerUser(UserRequestModel userRequestModel) {
        // Check if user with the provided email already exists
        Optional<User> user = userRepo.findByEmail(userRequestModel.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }

        // Validate email format
        String email = userRequestModel.getEmail();
        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Invalid email format: " , email);
        }
        // Validate password strength
        String password = userRequestModel.getPassword();
        if (!isValidPassword(password)) {
            throw new InvalidPasswordException("Invalid password format. It should contain at least 8 characters, " +
                    "including at least 1 uppercase letter and 1 symbol.");
        }

        // Get roles for the user
        List<String> roles = userRequestModel.getRoles();
        List<Role> toBeAddedRoles = new ArrayList<>();
        for (String role : roles) {
            Role requiredRole = roleService.getRoles(role);
            if (requiredRole != null) {
                toBeAddedRoles.add(requiredRole);
            } else {
                throw new RoleNotFoundException("No such roles");
            }
        }

        // Create and save the user
        User requiredUser = User.builder()
                .firstName(userRequestModel.getFirstName())
                .lastName(userRequestModel.getLastName())
                .email(userRequestModel.getEmail())
                .password(passwordEncoder.encode(userRequestModel.getPassword()))
                .address(userRequestModel.getAddress())
                .roles(toBeAddedRoles)
                .build();
        User savedUser = userRepo.save(requiredUser);

        // Return the authentication response
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(savedUser))
                .build();
    }

    private boolean isValidEmail(String email) {
        // Regular expression pattern for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        // Regular expression pattern for password validation
        String passwordRegex = "^(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$";
        return password.matches(passwordRegex);
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequestModel requestModel) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestModel.getEmail(), requestModel.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<User> user = userRepo.findByEmail(requestModel.getEmail());
        if (user.isPresent()) {
            User requiredUser = user.get();
            String jwtToken = jwtService.generateToken(requiredUser);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    /*public AuthenticationResponse login(AuthenticationRequestModel requestModel){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getEmail(), requestModel.getPassword()));
        Optional<User> user = userRepo.findByEmail(requestModel.getEmail());
        if(user.isPresent()){
            User requiredUser = user.get();
            var jwtToken = jwtService.generateToken(requiredUser);
            return AuthenticationResponse.builder()
                    .token(jwtToken).build();
        }

        else{
            throw new UsernameNotFoundException("Not found");
        }
    }*/
}
