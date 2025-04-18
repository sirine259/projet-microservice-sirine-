package com.backend.chatbot.Controllers;

import com.backend.chatbot.DTO.ChatRequest;
import com.backend.chatbot.DTO.ChatResponse;
import com.backend.chatbot.DTO.UserDTO;
import com.backend.chatbot.client.UserServiceClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.backend.chatbot.security.JwtUser;
import com.backend.chatbot.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {
    private final ChatService chatService;
    private final UserServiceClient userServiceClient;

    public ChatController(ChatService chatService, UserServiceClient userServiceClient) {
        this.chatService = chatService;
        this.userServiceClient = userServiceClient;
    }

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request,
                                           @AuthenticationPrincipal(errorOnInvalidType = false) JwtUser jwtUser) {
        try {
            String userId = "anonymous";
            if (jwtUser != null) {
                // Si l'utilisateur est authentifié, récupérer son ID
                UserDTO user = userServiceClient.getUserByUsername(jwtUser.getUsername());
                userId = String.valueOf(user.getId());
            }

            // Valider la requête
            if (request == null || request.getMessages() == null || request.getMessages().isEmpty()) {
                return ResponseEntity.badRequest().body(new ChatResponse("La requête est invalide"));
            }

            // Appeler le service avec la requête et l'ID de l'utilisateur
            ChatResponse response = chatService.handleChatRequest(request, userId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ChatResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ChatResponse("Une erreur s'est produite : " + e.getMessage()));
        }
    }
}