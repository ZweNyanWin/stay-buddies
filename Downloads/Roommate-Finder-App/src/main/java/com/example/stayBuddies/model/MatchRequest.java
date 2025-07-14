package com.example.stayBuddies.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="match_requests")
public class MatchRequest {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="from_user_id")
    private Student fromUser;

    @ManyToOne(optional = false)
    @JoinColumn(name="to_user_id")
    private Student toUser;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private LocalDateTime sentAt = LocalDateTime.now();

    public enum Status { PENDING, ACCEPTED, REJECTED }

}
