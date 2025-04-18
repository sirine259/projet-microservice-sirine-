package com.backend.chatbot.Repository;

import com.backend.chatbot.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, String> {
    List<Conversation> findByUserIdOrderByUpdatedAtDesc(String userId);
    Optional<Conversation> findByIdAndUserId(String id, String userId);
}