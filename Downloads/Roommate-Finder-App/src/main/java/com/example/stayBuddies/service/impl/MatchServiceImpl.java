package com.example.stayBuddies.service.impl;

import com.example.stayBuddies.model.Match;
import com.example.stayBuddies.model.QuizAnswer;
import com.example.stayBuddies.model.Student;
import com.example.stayBuddies.repository.MatchRepository;
import com.example.stayBuddies.repository.QuizAnswerRepository;
import com.example.stayBuddies.repository.StudentRepository;
import com.example.stayBuddies.service.MatchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class MatchServiceImpl implements MatchService {

    private final QuizAnswerRepository quizRepo;
    private final StudentRepository userRepo;
    private final MatchRepository      matchRepo;

    @Autowired
    public MatchServiceImpl(QuizAnswerRepository quizRepo,
                            StudentRepository userRepo,
                            MatchRepository matchRepo) {
        this.quizRepo = quizRepo;
        this.userRepo = userRepo;
        this.matchRepo = matchRepo;
    }

    @Override
    public long countTotalMatches() {
        return matchRepo.count();
    }

    @Override
    public List<Match> generateMatchesForUser(Long userId, int topN) {
        // 0) load the requesting student (or fail)
        Student baseUser = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // 1) fetch their quiz answers
        Map<Integer, Integer> baseAnswers = quizRepo.findByUserId(userId).stream()
                .collect(toMap(QuizAnswer::getQuestionNumber,
                        QuizAnswer::getAnswerValue));

        // 2) fetch all other students
        List<Student> others = userRepo.findAll().stream()
                .filter(u -> !u.getId().equals(userId))
                .collect(toList());

        // 3) score and build Match objects
        List<Match> matches = new ArrayList<>();
        for (Student other : others) {
            Map<Integer, Integer> theirAnswers = quizRepo.findByUserId(other.getId())
                    .stream()
                    .collect(toMap(QuizAnswer::getQuestionNumber,
                            QuizAnswer::getAnswerValue));

            double score = compatibilityScore(baseAnswers, theirAnswers);
            // use the convenience ctor we added to Match.java:
            matches.add(new Match(baseUser, other, score));  // jpa will override if 5 args constructor is used
        }

        // 4) sort and pick top N
        matches.sort(Comparator.comparingDouble(Match::getScore).reversed());
        List<Match> topMatches = matches.stream()
                .limit(topN)
                .collect(toList());

        // 5) save and return
        return matchRepo.saveAll(topMatches);
    }

    private double compatibilityScore(
            Map<Integer, Integer> a,
            Map<Integer, Integer> b) {
        double sumDiff = 0;
        for (int q = 1; q <= 10; q++) {
            sumDiff += Math.abs(a.get(q) - b.get(q));
        }
        // max diff = 4 * 10 = 40
        return 1.0 - (sumDiff / 40.0);
    }

    @Override
    public List<Match> findMatchesForUser(Long userId) {
        return matchRepo.findByUser1IdOrUser2Id(userId, userId);
    }
}
