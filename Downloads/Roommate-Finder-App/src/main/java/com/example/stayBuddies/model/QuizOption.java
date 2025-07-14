package com.example.stayBuddies.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "quiz_options")
public class QuizOption {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private QuizQuestion question;

    private String label;
    private Integer value;    // e.g. 1–5 scale
}