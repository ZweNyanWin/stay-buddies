package com.example.stayBuddies.service.impl;

import com.example.stayBuddies.model.QuizAnswer;
import com.example.stayBuddies.repository.QuizAnswerRepository;
import com.example.stayBuddies.repository.StudentRepository;
import com.example.stayBuddies.service.QuizAnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizAnswerServiceImpl implements QuizAnswerService {
    private final QuizAnswerRepository answerRepo;
    private final StudentRepository userRepo;

    public QuizAnswerServiceImpl(QuizAnswerRepository answerRepo,
                                 StudentRepository userRepo) {
        this.answerRepo = answerRepo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public void saveUserAnswers(Long userId, Map<Long, Integer> answers) {
        var user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("No student"));

        List<QuizAnswer> entities = answers.entrySet().stream()
                .map(e -> {
                    QuizAnswer a = new QuizAnswer();
                    a.setUser(user);
                    a.setQuestionNumber(e.getKey().intValue());  // if you’re mapping by questionId
                    a.setAnswerValue(e.getValue());
                    return a;
                })
                .collect(Collectors.toList());

        answerRepo.saveAll(entities);
    }
}
