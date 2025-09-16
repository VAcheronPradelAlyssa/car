package com.example.chat.controller;

import com.example.chat.entity.Message;
import com.example.chat.entity.User;
import com.example.chat.entity.CustomerService;
import com.example.chat.service.MessageService;
import com.example.chat.service.UserService;
import com.example.chat.service.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerServiceService customerServiceService;

    @GetMapping
    public ResponseEntity<List<Message>> getMessagesByChatSession(@RequestParam(required = false) Long chatSessionId) {
        List<Message> messages;
        if (chatSessionId != null) {
            messages = messageService.getMessagesByChatSessionId(chatSessionId);
        } else {
            messages = messageService.getAllMessages();
        }
        if (messages == null) {
            messages = java.util.Collections.emptyList();
        }
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{id}")
    public Optional<Message> getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        // Si aucun sender/receiver n'est renseigné mais on a un username, on cherche l'utilisateur ou le conseiller
        if (message.getSenderUser() == null && message.getSenderCustomerService() == null &&
            message.getReceiverUser() == null && message.getReceiverCustomerService() == null && message.getContent() != null) {
            // On suppose que le frontend envoie senderUsername et receiverUsername dans le JSON
            String senderUsername = null;
            String receiverUsername = null;
            try {
                var json = new com.fasterxml.jackson.databind.ObjectMapper().readTree(message.getContent());
                senderUsername = json.has("sender") ? json.get("sender").asText() : null;
                receiverUsername = json.has("receiver") ? json.get("receiver").asText() : null;
            } catch (Exception ignored) {}
            if (senderUsername != null) {
                User user = userService.getUserByUsername(senderUsername);
                if (user != null) message.setSenderUser(user);
                else {
                    CustomerService cs = customerServiceService.getByUsername(senderUsername);
                    if (cs != null) message.setSenderCustomerService(cs);
                }
            }
            if (receiverUsername != null) {
                User user = userService.getUserByUsername(receiverUsername);
                if (user != null) message.setReceiverUser(user);
                else {
                    CustomerService cs = customerServiceService.getByUsername(receiverUsername);
                    if (cs != null) message.setReceiverCustomerService(cs);
                }
            }
        }
        return messageService.saveMessage(message);
    }

    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable Long id, @RequestBody Message message) {
        message.setId(id);
        return messageService.saveMessage(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}
