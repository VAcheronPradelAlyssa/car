package com.example.chat.controller;

import com.example.chat.entity.ChatSession;
import com.example.chat.service.ChatSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chatsessions")
public class ChatSessionController {
    @Autowired
    private ChatSessionService chatSessionService;

    @GetMapping
    public List<ChatSession> getAllChatSessions() {
        return chatSessionService.getAllChatSessions();
    }

    @GetMapping("/{id}")
    public Optional<ChatSession> getChatSessionById(@PathVariable Long id) {
        return chatSessionService.getChatSessionById(id);
    }

    @PostMapping
    public ChatSession createChatSession(@RequestBody ChatSession chatSession) {
        if (chatSession.getStartedAt() == null) {
            chatSession.setStartedAt(java.time.LocalDateTime.now());
        }
        return chatSessionService.saveChatSession(chatSession);
    }

    @PutMapping("/{id}")
    public ChatSession updateChatSession(@PathVariable Long id, @RequestBody ChatSession chatSession) {
        chatSession.setId(id);
        return chatSessionService.saveChatSession(chatSession);
    }

    @DeleteMapping("/{id}")
    public void deleteChatSession(@PathVariable Long id) {
        chatSessionService.deleteChatSession(id);
    }
}
