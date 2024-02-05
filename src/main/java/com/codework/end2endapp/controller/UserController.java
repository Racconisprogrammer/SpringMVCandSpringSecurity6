package com.codework.end2endapp.controller;

import com.codework.end2endapp.entity.User;
import com.codework.end2endapp.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    @GetMapping
    public String getUsers(Model model, Authentication authentication) {
        System.out.println("Current 11111 "+ authentication.getAuthorities());
        model.addAttribute("users", userService.getAllUsers());
        System.out.println(userService.getAllUsers()+"----------------------------------------------------------");
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateFprm(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userService.findById(id);
        model.addAttribute("user", user.get());
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user){
        userService.updateUser(id, user.getFirstName(), user.getLastName(), user.getEmail());
        return "redirect:/users?update_success";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        System.out.println("-------------------------------------------------------------------------------");
        userService.deleteUser(id);
        System.out.println("===============================================================================");
        return "redirect:/users?delete_success";
    }

}
