package com.codework.end2endapp.service;

import com.codework.end2endapp.entity.Role;
import com.codework.end2endapp.entity.User;
import com.codework.end2endapp.registration.RegistrationRequest;
import com.codework.end2endapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest registrationRequest) {
        var user = new User(registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                Arrays.asList(new Role("ROLE_USER")));
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).
                orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
