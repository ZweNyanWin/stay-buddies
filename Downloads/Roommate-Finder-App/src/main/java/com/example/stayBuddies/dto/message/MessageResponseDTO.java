package com.example.stayBuddies.dto.message;

import java.time.Instant;

public class MessageResponseDTO {
    private Long id;
    private Long senderId;
    private Long recipientId;
    private String content;
    private Instant sentAt;
    private boolean read;

    public MessageResponseDTO() { }

    public MessageResponseDTO(Long id,
                              Long senderId,
                              Long recipientId,
                              String content,
                              Instant sentAt,
                              boolean read) {
        this.id          = id;
        this.senderId    = senderId;
        this.recipientId = recipientId;
        this.content     = content;
        this.sentAt      = sentAt;
        this.read        = read;
    }

    // ── getters & setters ──────────────────────────────────────────────
    public Long getId()                { return id; }
    public void setId(Long id)         { this.id = id; }
    public Long getSenderId()          { return senderId; }
    public void setSenderId(Long s)    { this.senderId = s; }
    public Long getRecipientId()       { return recipientId; }
    public void setRecipientId(Long r) { this.recipientId = r; }
    public String getContent()         { return content; }
    public void setContent(String c)   { this.content = c; }
    public Instant getSentAt()         { return sentAt; }
    public void setSentAt(Instant t)   { this.sentAt = t; }
    public boolean isRead()            { return read; }
    public void setRead(boolean read)  { this.read = read; }
}
