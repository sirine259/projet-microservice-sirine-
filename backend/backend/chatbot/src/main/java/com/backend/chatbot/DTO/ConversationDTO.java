package com.backend.chatbot.DTO;

import com.backend.chatbot.model.Conversation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ConversationDTO {
    private String id;
    private String title;
    private Instant createdAt;
    private Instant updatedAt;
    private List<MessageDTO> messages;
    private String topic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

    public static ConversationDTO fromEntity(Conversation conversation) {
        ConversationDTO dto = new ConversationDTO();
        dto.setId(conversation.getId());
        dto.setTitle(conversation.getTitle());
        dto.setTopic(conversation.getTopic());
        dto.setCreatedAt(conversation.getCreatedAt() != null
                ? conversation.getCreatedAt().toInstant(ZoneOffset.UTC)
                : Instant.now());
        dto.setUpdatedAt(conversation.getUpdatedAt() != null
                ? conversation.getUpdatedAt().toInstant(ZoneOffset.UTC)
                : Instant.now());
        dto.setMessages(conversation.getMessages().stream()
                .map(MessageDTO::fromEntity)
                .collect(Collectors.toList()));
        return dto;
    }
}