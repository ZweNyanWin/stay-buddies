package com.example.stayBuddies.service;

import com.example.stayBuddies.model.Match;

import java.util.List;

public interface MatchService {
    long countTotalMatches();
    List<Match> generateMatchesForUser(Long userId, int topN);
    List<Match> findMatchesForUser(Long userId);
}
