package com.example.stayBuddies.controller;

import com.example.stayBuddies.service.MatchRequestService;
import com.example.stayBuddies.model.MatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.stayBuddies.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/requests")
public class MatchRequestController {

    private final MatchRequestService reqService;

    @Autowired
    public MatchRequestController(MatchRequestService reqService) {
        this.reqService = reqService;
    }

    /** AJAX or form post to send a request */
    @PostMapping("/send")
    public String send(@RequestParam Long toUserId,
                       @AuthenticationPrincipal Student currentUser) {
        reqService.sendRequest(currentUser.getId(), toUserId);
        return "redirect:/matches";  // back to match list
    }

    /** show pending requests */
    @GetMapping
    public String inbox(@AuthenticationPrincipal Student currentUser,
                        Model model) {
        model.addAttribute("requests",
                reqService.getPendingForUser(currentUser.getId()));
        return "students/request-inbox";
    }

    /** accept or reject */
    @PostMapping("/{id}/respond")
    public String respond(@PathVariable Long id,
                          @RequestParam("action") String action) {
        MatchRequest.Status status =
                action.equals("accept")
                        ? MatchRequest.Status.ACCEPTED
                        : MatchRequest.Status.REJECTED;
        reqService.respond(id, status);
        return "redirect:/requests";
    }
}
