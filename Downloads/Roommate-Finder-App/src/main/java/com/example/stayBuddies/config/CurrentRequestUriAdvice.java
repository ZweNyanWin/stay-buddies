package com.example.stayBuddies.config;

import com.example.stayBuddies.model.Student;
import com.example.stayBuddies.service.MatchRequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentRequestUriAdvice {

    private final MatchRequestService reqService;

    public CurrentRequestUriAdvice(MatchRequestService reqService) {
        this.reqService = reqService;
    }

    @ModelAttribute("currentUri")
    public String currentUri(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute
    public void globalAttrs(Model m,
                            @AuthenticationPrincipal Student currentUser) {
        if (currentUser != null) {
            m.addAttribute("pendingCount",
                    reqService.countPendingForUser(currentUser.getId()));
            m.addAttribute("requests",
                    reqService.getPendingForUser(currentUser.getId()));
        }
    }

}
