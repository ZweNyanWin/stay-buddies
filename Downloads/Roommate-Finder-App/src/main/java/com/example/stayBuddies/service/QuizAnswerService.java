package com.example.stayBuddies.service;

import java.util.Map;

public interface QuizAnswerService {
    /** Persist this student’s answers (questionId → value) */
    void saveUserAnswers(Long userId, Map<Long,Integer> answers);
}
