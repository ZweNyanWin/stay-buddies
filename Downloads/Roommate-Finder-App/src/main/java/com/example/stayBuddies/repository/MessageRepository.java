package com.example.stayBuddies.repository;

import com.example.stayBuddies.model.Message;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("""
      SELECT m FROM Message m
       WHERE (m.sender.id = :you   AND m.recipient.id = :them)
          OR (m.sender.id = :them AND m.recipient.id = :you)
       ORDER BY m.sentAt
    """)
    List<Message> findConversation(
            @Param("you") Long you,
            @Param("them") Long them
    );
}
