package com.example.chat.service;

import com.example.chat.entity.Message;
import com.example.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public List<Message> getMessagesByChatSession(Long chatSessionId) {
        return messageRepository.findByChatSessionId(chatSessionId);
    }

    public List<Message> getMessagesByChatSessionId(Long chatSessionId) {
        return messageRepository.findByChatSessionId(chatSessionId);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
