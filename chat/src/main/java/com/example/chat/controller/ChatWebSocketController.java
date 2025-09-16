package com.example.chat.controller;

import com.example.chat.entity.Message;
import com.example.chat.entity.ChatSession;
import com.example.chat.entity.User;
import com.example.chat.entity.CustomerService;
import com.example.chat.service.MessageService;
import com.example.chat.service.ChatSessionService;
import com.example.chat.service.UserService;
import com.example.chat.service.CustomerServiceService;
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
    @Autowired
    private CustomerServiceService customerServiceService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage message) {
        ChatSession chatSession = null;
        if (message.getChatSessionId() != null) {
            chatSession = chatSessionService.getChatSessionById(message.getChatSessionId()).orElse(null);
        }

        // Création de la session si besoin
        if (chatSession == null) {
            User client = message.getSenderUser() != null ? message.getSenderUser() : message.getReceiverUser();
            CustomerService advisor = message.getSenderCustomerService() != null ? message.getSenderCustomerService() : message.getReceiverCustomerService();
            chatSession = ChatSession.builder()
                .client(client)
                .advisor(advisor)
                .startedAt(LocalDateTime.now())
                .build();
            chatSession = chatSessionService.saveChatSession(chatSession);
        }

        Message.MessageBuilder msgBuilder = Message.builder()
            .content(message.getContent())
            .timestamp(LocalDateTime.now())
            .chatSession(chatSession);

        if (message.getSenderUser() != null) {
            msgBuilder.senderUser(userService.getUserById(message.getSenderUser().getId()).orElse(null));
        }
        if (message.getSenderCustomerService() != null) {
            msgBuilder.senderCustomerService(customerServiceService.getCustomerServiceById(message.getSenderCustomerService().getId()).orElse(null));
        }
        if (message.getReceiverUser() != null) {
            msgBuilder.receiverUser(userService.getUserById(message.getReceiverUser().getId()).orElse(null));
        }
        if (message.getReceiverCustomerService() != null) {
            msgBuilder.receiverCustomerService(customerServiceService.getCustomerServiceById(message.getReceiverCustomerService().getId()).orElse(null));
        }

        Message msg = msgBuilder.build();
        messageService.saveMessage(msg);
        message.setChatSessionId(chatSession.getId());
        return message;
    }
}
