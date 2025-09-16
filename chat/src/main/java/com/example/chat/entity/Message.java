package com.example.chat.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "MESSAGE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_session_id")
    private ChatSession chatSession;

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "sender_customer_service_id")
    private CustomerService senderCustomerService;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    private User receiverUser;

    @ManyToOne
    @JoinColumn(name = "receiver_customer_service_id")
    private CustomerService receiverCustomerService;

    @Column(columnDefinition = "TEXT")
    private String content;

    private java.time.LocalDateTime timestamp;
}