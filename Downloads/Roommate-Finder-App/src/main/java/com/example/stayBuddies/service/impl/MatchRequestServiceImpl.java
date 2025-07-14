package com.example.stayBuddies.service.impl;

import com.example.stayBuddies.model.MatchRequest;
import com.example.stayBuddies.repository.MatchRequestRepository;
import com.example.stayBuddies.repository.StudentRepository;
import com.example.stayBuddies.service.MatchRequestService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchRequestServiceImpl implements MatchRequestService {


    private final MatchRequestRepository reqRepo;
    private final StudentRepository userRepo;

    public MatchRequestServiceImpl(MatchRequestRepository reqRepo,
                                   StudentRepository userRepo) {
        this.reqRepo = reqRepo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public void sendRequest(Long fromUserId, Long toUserId) {
        if (reqRepo.existsByFromUserIdAndToUserIdAndStatus(fromUserId, toUserId, MatchRequest.Status.PENDING)) {
            return;
        }
        var from = userRepo.findById(fromUserId).orElseThrow();
        var to = userRepo.findById(toUserId).orElseThrow();
        MatchRequest mr = new MatchRequest();
        mr.setFromUser(from);
        mr.setToUser(to);
        reqRepo.save(mr);

    }

    @Override
    public List<MatchRequest> getPendingForUser(Long toUserId) {
        return reqRepo.findByToUserIdAndStatus(toUserId, MatchRequest.Status.PENDING);
    }

    @Override
    @Transactional
    public void respond(Long requestId, MatchRequest.Status newStatus) {
        MatchRequest mr = reqRepo.findById(requestId).orElseThrow();
        mr.setStatus(newStatus);

    }

    @Override
    public long countPendingForUser(Long toUserId) {
        return getPendingForUser(toUserId).size();
    }
}
