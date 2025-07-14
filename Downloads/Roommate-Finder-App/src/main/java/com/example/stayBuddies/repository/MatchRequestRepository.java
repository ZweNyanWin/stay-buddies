package com.example.stayBuddies.repository;

import com.example.stayBuddies.model.MatchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatchRequestRepository
        extends JpaRepository<MatchRequest,Long> {
    List<MatchRequest> findByToUserIdAndStatus(Long toUserId, MatchRequest.Status status);
    boolean existsByFromUserIdAndToUserIdAndStatus(Long from, Long to, MatchRequest.Status status);
}
