package com.codework.end2endapp.registration.token;

import com.codework.end2endapp.entity.User;

import java.util.Optional;

public interface IVerificationTokenService {

    String validateToken(String token);

    void saveVerificationTokenForUser(User user, String token);

    Optional<VerificationToken> findByToken(String token);


    void deleteUserToken(Long id);

}
