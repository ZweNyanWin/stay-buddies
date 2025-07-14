package com.example.stayBuddies.dto;

public class ChatMessage {

    /**
     * The ID of the student you’re sending this message _to_.
     */
    private Long toUserId;

    /**
     * The text content of your chat message.
     */
    private String content;

    public ChatMessage() {}

    public ChatMessage(Long toUserId, String content) {
        this.toUserId = toUserId;
        this.content  = content;
    }

    // ── getters & setters ──────────────────────────────────────────────
    public Long getToUserId()             { return toUserId; }
    public void setToUserId(Long toUserId){ this.toUserId = toUserId; }
    public String getContent()            { return content; }
    public void setContent(String content){ this.content = content; }
}