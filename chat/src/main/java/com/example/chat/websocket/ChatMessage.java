package com.example.chat.websocket;

import lombok.Data;
import com.example.chat.entity.User;
import com.example.chat.entity.CustomerService;

@Data
public class ChatMessage {
    private String content;
    private Long chatSessionId;
    private User senderUser;
    private CustomerService senderCustomerService;
    private User receiverUser;
    private CustomerService receiverCustomerService;
}
