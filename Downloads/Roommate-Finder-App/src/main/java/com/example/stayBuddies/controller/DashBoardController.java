package com.example.stayBuddies.controller;


import com.example.stayBuddies.service.MatchService;
import com.example.stayBuddies.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class DashBoardController {

    private final StudentService userService;
    private final MatchService matchService;

    @Autowired
    public DashBoardController(StudentService userService , MatchService matchService) {
        this.userService = userService;
        this.matchService = matchService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalUsers", userService.countAll());
        model.addAttribute("activeUsers", userService.countActiveLast30Days());
        // if you later want matchesMade:
        model.addAttribute("matchesMade", matchService.countTotalMatches());
        return "dashboard";      // <- looks for templates/dashboard.html
    }

}
