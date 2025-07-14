package com.example.stayBuddies.service;

import com.example.stayBuddies.model.MatchRequest;

import java.util.List;

public interface MatchRequestService {
    void sendRequest(Long fromUserId, Long toUserId);
    List<MatchRequest> getPendingForUser(Long toUserId);
    void respond(Long requestId, MatchRequest.Status newStatus);
    long countPendingForUser(Long toUserId);
}
