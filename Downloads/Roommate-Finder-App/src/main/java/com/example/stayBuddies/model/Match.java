package com.example.stayBuddies.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who requested the match
    @ManyToOne(optional = false)
    @JoinColumn(name = "user1_id", nullable = false)
    private Student user1;

    // who we’re matching against
    @ManyToOne(optional = false)
    @JoinColumn(name = "user2_id", nullable = false)
    private Student user2;

    private double score;

    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "matched_at", nullable = false, updatable = false)
    private LocalDateTime matchedAt;


    public Match() {
        // JPA
    }

    /**
     * full‐args constructor (including id)
     */
    public Match(Long id,
                 Student user1,
                 Student user2,
                 double score,
                 LocalDateTime createdAt) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.score = score;
        this.createdAt = createdAt;
    }

    /**
     * convenience ctor: new match now, no id yet
     */
    public Match(Student user1, Student user2, double score) {
        this(null, user1, user2, score, LocalDateTime.now());
    }
}
