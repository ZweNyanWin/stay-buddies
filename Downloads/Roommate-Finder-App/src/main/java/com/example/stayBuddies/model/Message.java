package com.example.stayBuddies.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Student sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    private Student recipient;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Instant sentAt = Instant.now();
    // our backing field
    @Column(name = "read_flag")
    private boolean readFlag = false;

    /**
     * Expose this as "isRead()" so your DTO/controller code can call message.isRead()
     */
    public boolean isRead() {
        return this.readFlag;
    }

    /**
     * And a matching setter if you ever need to mark messages read.
     */
    public void setRead(boolean read) {
        this.readFlag = read;
    }
}
