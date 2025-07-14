package com.example.stayBuddies.controller;

import com.example.stayBuddies.dto.message.MessageResponseDTO;
import com.example.stayBuddies.model.Message;
import com.example.stayBuddies.repository.MessageRepository;
import com.example.stayBuddies.service.StudentService;
import com.example.stayBuddies.dto.student.StudentResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/message ,s")
public class MessageRestController {

    private final MessageRepository messageRepo;
    private final StudentService userService;

    @Autowired
    public MessageRestController(MessageRepository messageRepo,
                                 StudentService userService) {
        this.messageRepo = messageRepo;
        this.userService = userService;
    }

    /**
     * Fetch the full conversation (history) between the logged-in student
     * and the “other” student whose ID is in the path.
     */
    @GetMapping("/{otherUserId}")
    public List<MessageResponseDTO> getConversation(
            @PathVariable Long otherUserId,
            Principal principal
    ) {
        // 1) find “me” by email (principal.getName())
        String myEmail = principal.getName();
        StudentResponseDTO meDto = userService.findByEmail(myEmail);
        if (meDto == null) {
            throw new EntityNotFoundException("Current student not found: " + myEmail);
        }

        // 2) ensure the “other” exists (this will throw if not)
        userService.getUserById(otherUserId);

        // 3) load messages
        List<Message> history = messageRepo.findConversation(meDto.getId(), otherUserId);

        // 4) map to DTOs
        return history.stream()
                .map(m -> new MessageResponseDTO(
                        m.getId(),
                        m.getSender().getId(),
                        m.getRecipient().getId(),
                        m.getContent(),
                        m.getSentAt(),
                        m.isRead()   // or getReadFlag() depending on your field
                ))
                .collect(Collectors.toList());
    }
}
