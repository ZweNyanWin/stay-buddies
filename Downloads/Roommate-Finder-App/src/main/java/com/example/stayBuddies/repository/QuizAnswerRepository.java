package com.example.stayBuddies.repository;

import com.example.stayBuddies.model.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizAnswerRepository
        extends JpaRepository<QuizAnswer, Long> {
    // fetch all answers for one student
    List<QuizAnswer> findByUserId(Long userId);
}