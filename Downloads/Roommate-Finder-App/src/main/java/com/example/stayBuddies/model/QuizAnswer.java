package com.example.stayBuddies.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "quiz_answers")
public class QuizAnswer {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "user_id")
    private Student user;

    // assuming questions are numbered 1…10
    private Integer questionNumber;

    // e.g. on a 1–5 scale
    private Integer answerValue;
}