package com.example.stayBuddies.controller;

import com.example.stayBuddies.dto.student.StudentResponseDTO;
import com.example.stayBuddies.model.Student;
import com.example.stayBuddies.repository.StudentRepository;
import com.example.stayBuddies.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {
    private final StudentService userService;
    private final StudentRepository userRepository;


    @Autowired
    public AdminUserController(StudentService userService, StudentRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public String viewUsers(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model
    ) {
        List<StudentResponseDTO> list = (keyword == null || keyword.isBlank())
                ? userService.getAllUsers()
                : userService.searchUsersByName(keyword);

        model.addAttribute("users", list);
        model.addAttribute("keyword", keyword);
        return "admins/user-management";
    }

    @GetMapping("/view/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        StudentResponseDTO user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admins/user-details";
    }

    @GetMapping("/suspend/{id}")
    public String suspendUser(@PathVariable Long id) {
        Student user = userRepository.findById(id).orElseThrow();
        user.setActive(false);
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }


}


