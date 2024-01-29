package com.codework.end2endapp.service;

import com.codework.end2endapp.entity.User;
import com.codework.end2endapp.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getAllUsers();

    User registerUser(RegistrationRequest registrationRequest);

    Optional<User> findByEmail(String email);
}
