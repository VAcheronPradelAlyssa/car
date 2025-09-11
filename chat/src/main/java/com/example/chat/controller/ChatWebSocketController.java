package com.example.chat.controller;

import com.example.chat.entity.Message;
import com.example.chat.entity.ChatSession;
import com.example.chat.entity.User;
import com.example.chat.service.MessageService;
import com.example.chat.service.ChatSessionService;
import com.example.chat.service.UserService;
import com.example.chat.websocket.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatWebSocketController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatSessionService chatSessionService;
    @Autowired
    private UserService userService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage message) {
        ChatSession chatSession = null;
        if (message.getChatSessionId() != null) {
            chatSession = chatSessionService.getChatSessionById(message.getChatSessionId()).orElse(null);
        }
        if (chatSession == null) {
            User client = userService.getUserByUsername(message.getSender());
            User advisor = userService.getUserByUsername(message.getReceiver());
            chatSession = ChatSession.builder()
                .client(client)
                .advisor(advisor)
                .startedAt(LocalDateTime.now())
                .build();
            chatSession = chatSessionService.saveChatSession(chatSession);
        }
        Message msg = Message.builder()
            .content(message.getContent())
            .timestamp(LocalDateTime.now())
            .sender(userService.getUserByUsername(message.getSender()))
            .receiver(userService.getUserByUsername(message.getReceiver()))
            .chatSession(chatSession)
            .build();
        messageService.saveMessage(msg);
        message.setChatSessionId(chatSession.getId());
        return message;
    }
}
