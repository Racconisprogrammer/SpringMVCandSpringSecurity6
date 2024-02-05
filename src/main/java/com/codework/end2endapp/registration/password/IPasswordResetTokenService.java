package com.codework.end2endapp.registration.password;


import com.codework.end2endapp.entity.User;

import java.util.Optional;

public interface IPasswordResetTokenService {

    String validatePasswordResetToken(String theToken);

    Optional<User> findUserByPasswordResetToken(String theToken);

    void resetPassword(User theUser, String password);

    void createPasswordResetTokenForUser(User user, String passwordResetToken);
}
