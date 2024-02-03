package com.codework.end2endapp.controller;


import com.codework.end2endapp.entity.User;
import com.codework.end2endapp.event.RegistrationCompleteEvent;
import com.codework.end2endapp.event.listener.RegistrationCompleteEventListener;
import com.codework.end2endapp.registration.RegistrationRequest;
import com.codework.end2endapp.registration.token.VerificationToken;
import com.codework.end2endapp.registration.token.VerificationTokenRepository;
import com.codework.end2endapp.registration.token.VerificationTokenService;
import com.codework.end2endapp.service.IUserService;
import com.codework.end2endapp.service.UserService;
import com.codework.end2endapp.utility.UrlUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final IUserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenService verificationTokenService;
    private final RegistrationCompleteEventListener eventListener;


    @GetMapping("/registration-form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationRequest());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationRequest registration, HttpServletRequest request) {
        User user = userService.registerUser(registration);
        publisher.publishEvent(new RegistrationCompleteEvent(user, UrlUtil.getApplicationUrl(request)));
        return "redirect:/registration/registration-form?success";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        Optional<VerificationToken> theToken = verificationTokenService.findByToken(token);
        if (theToken.isPresent() && theToken.get().getUser().isEnabled()) {
            return "redirect:/login?verified";
        }
        String verificationResult = verificationTokenService.validateToken(token);
        switch (verificationResult.toLowerCase()) {
            case "expired":
                return "redirect:/error?expired";
            case "valid":
                return "redirect:/login?valid";
            default:
                return "redirect:/error?invalid";
        }
    }
}
