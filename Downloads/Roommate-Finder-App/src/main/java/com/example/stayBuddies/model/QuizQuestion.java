package com.example.stayBuddies.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "quiz_questions")
public class QuizQuestion {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<QuizOption> options = new ArrayList<>();

}