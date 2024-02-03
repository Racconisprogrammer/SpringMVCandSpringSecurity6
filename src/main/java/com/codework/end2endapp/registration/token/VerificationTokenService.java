package com.codework.end2endapp.registration.token;


import com.codework.end2endapp.entity.User;
import com.codework.end2endapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class VerificationTokenService implements IVerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;

    @Override
    public String validateToken(String token) {
        Optional<VerificationToken> token1 =
                verificationTokenRepository.findByToken(token);
        if (token1.isEmpty()) {
            return "INVALID";
        }
        User user = token1.get().getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token1.get().getExpirationTime()
                .getTime() - calendar.getTime().getTime()) <= 0) {
            return "EXPIRED";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "VALID";
    }

    @Override
    public void saveVerificationTokenForUser(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);

    }

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void deleteUserToken(Long id) {

    }
}
