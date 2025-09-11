package com.example.chat.websocket;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
    private String receiver;
    private Long chatSessionId;
}
