package com.example.chat.service;

import com.example.chat.entity.ChatSession;
import com.example.chat.repository.ChatSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChatSessionService {
    @Autowired
    private ChatSessionRepository chatSessionRepository;

    public List<ChatSession> getAllChatSessions() {
        return chatSessionRepository.findAll();
    }

    public Optional<ChatSession> getChatSessionById(Long id) {
        return chatSessionRepository.findById(id);
    }

    public List<ChatSession> getChatSessionsByClient(Long clientId) {
        return chatSessionRepository.findByClientId(clientId);
    }

    public List<ChatSession> getChatSessionsByAdvisor(Long advisorId) {
        return chatSessionRepository.findByAdvisorId(advisorId);
    }

    public ChatSession saveChatSession(ChatSession chatSession) {
        return chatSessionRepository.save(chatSession);
    }

    public void deleteChatSession(Long id) {
        chatSessionRepository.deleteById(id);
    }
}
