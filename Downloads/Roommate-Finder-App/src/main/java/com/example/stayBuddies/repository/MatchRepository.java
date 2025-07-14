package com.example.stayBuddies.repository;

import com.example.stayBuddies.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository
        extends JpaRepository<Match, Long> {
    // e.g. count all matches
    long count();

    // matches for a particular student
    List<Match> findByUser1IdOrUser2Id(Long u1, Long u2);
}
