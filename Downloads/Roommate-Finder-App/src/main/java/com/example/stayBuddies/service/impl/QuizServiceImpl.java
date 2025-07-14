package com.example.stayBuddies.service.impl;

import com.example.stayBuddies.model.QuizQuestion;
import com.example.stayBuddies.repository.QuizQuestionRepository;
import com.example.stayBuddies.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizQuestionRepository quizRepo;

    @Autowired
    public QuizServiceImpl(QuizQuestionRepository qr) {
        this.quizRepo = qr;
    }

    public List<QuizQuestion> getAllQuestions() {
        // eager‐fetch options
//        return quizRepo.findAll(Sort.by("id"));
        return quizRepo.findAll();
    }
}
