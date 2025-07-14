package com.example.stayBuddies.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ChatMessageDTO {

    private Long senderId;
    private String senderName;
    private String content;
    private Instant sentAt;
    private boolean read;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(Long senderId,
                          String senderName,
                          String content,
                          Instant sentAt,
                          boolean read) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.content = content;
        this.sentAt = sentAt;
        this.read = read;
    }
}