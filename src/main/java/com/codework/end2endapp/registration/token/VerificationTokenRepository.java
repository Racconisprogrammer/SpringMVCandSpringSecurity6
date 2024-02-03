package com.codework.end2endapp.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {


   Optional<VerificationToken> findByToken(String token);
}
