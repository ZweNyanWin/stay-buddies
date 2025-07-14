package com.example.stayBuddies.controller;

import com.example.stayBuddies.model.Match;
import com.example.stayBuddies.service.MatchService;
import com.example.stayBuddies.service.StudentService;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;
    private final StudentService userService;

    @Autowired
    public MatchController(MatchService ms, StudentService us) {
        this.matchService = ms;
        this.userService = us;
    }

    // e.g. hit this once the quiz is done
    @PostMapping("/generate")
    public String doGenerate(
            @RequestParam("userId") Long userId,
            Model model) {

        List<Match> topMatches =
                matchService.generateMatchesForUser(userId, 5);

        model.addAttribute("matches", topMatches);
        return "students/match-list";  // template to show your matches
    }

    @GetMapping
    public String list(@Nullable Principal principal, Model model) {
        List<Match> matches;

        if (principal != null) {
            // logged in: fetch real matches
            Long userId = userService.findByEmail(principal.getName()).getId();
            matches = matchService.findMatchesForUser(userId);
        } else {
            // anonymous: show no matches (or some placeholder)
            matches = Collections.emptyList();
        }

        model.addAttribute("matches", matches);
        return "users/matches";   // or "matches" depending on your folder structure
    }

}
