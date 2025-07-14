package com.example.stayBuddies.controller;

import com.example.stayBuddies.model.AppUser;
import com.example.stayBuddies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "students/signup";
    }


    @PostMapping("/signup")
    public String submitRegistrationForm(@ModelAttribute("user") AppUser user, Model model) {
        try {
            userService.registerNewUser(user);

        } catch (RuntimeException exception) {
            model.addAttribute("registrationError", exception.getMessage());
            return "students/signup";
        }

        return "redirect:/login?registered";

    }
}
