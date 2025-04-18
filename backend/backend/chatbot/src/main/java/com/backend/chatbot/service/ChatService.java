package com.backend.chatbot.service;

import com.backend.chatbot.DTO.ChatRequest;
import com.backend.chatbot.DTO.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    // Liste de mots-clés liés aux stages
    private static final List<String> STAGE_KEYWORDS = List.of(
            "stage", "internship", "postuler", "critères", "application", "durée",
            "entreprise", "cv", "lettre", "motivation", "entretien", "interview",
            "rémunération", "salaire", "convention", "école", "université", "formation",
            "étudiant", "candidature", "offre", "recherche", "emploi", "travail"
    );

    // Réponses fixes pour les questions liées aux stages
    private static final Map<String, String> STAGE_RESPONSES = new HashMap<>();

    static {
        STAGE_RESPONSES.put("stage", "Je peux vous aider avec toutes vos questions concernant les stages. Que souhaitez-vous savoir spécifiquement ?");
        // Ajoutez d'autres réponses ici...
    }

    private final AtomicReference<ConversationRepository> conversationRepository = new AtomicReference<ConversationRepository>();

    public ChatResponse handleChatRequest(ChatRequest request) {
        String message = request.getMessage().toLowerCase();

        for (String keyword : STAGE_KEYWORDS) {
            if (message.contains(keyword)) {
                String response = STAGE_RESPONSES.getOrDefault(keyword, "Désolé, je n'ai pas de réponse à ça.");
                return new ChatResponse(response);
            }
        }

        // Si aucun mot-clé ne correspond, lever une exception ou retourner une réponse par défaut
        throw new IllegalArgumentException("Sujet inconnu. Veuillez poser une question pertinente concernant les stages.");
    }

    public ConversationRepository getConversationRepository() {
        return conversationRepository.get();
    }

    public ChatResponse handleChatRequest(ChatRequest request, String userId) {
        return null;
    }

    private class ConversationRepository {
    }
}