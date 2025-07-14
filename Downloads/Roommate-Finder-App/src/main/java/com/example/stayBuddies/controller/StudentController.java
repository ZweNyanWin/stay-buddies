package com.example.stayBuddies.controller;

import com.example.stayBuddies.dto.student.StudentSignupDTO;
import com.example.stayBuddies.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
//    private final StudentService service;
//
//    public StudentController(StudentService service) {
//        this.service = service;
//    }
//
//    @GetMapping("/signup")
//    public String showSignupForm(Model model) {
//        model.addAttribute("user", new StudentSignupDTO());
//        return "users/signup";
//    }
//
//    @PostMapping("/signup")
//    public String processSignup(@ModelAttribute("user") StudentSignupDTO dto, Model model) {
//        try {
//            service.createUser(dto);
//            return "redirect:/signup-success";
//        } catch (RuntimeException e) {
//            model.addAttribute("error", e.getMessage());
//            return "users/signup";
//        }
//    }
//
//    @GetMapping("/signup-success")
//    public String signupSuccess() {
//        return "users/signup-success";
//    }
}
