package com.example.chat.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CHATSESSION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private CustomerService advisor;

    private java.time.LocalDateTime startedAt;

    @OneToMany(mappedBy = "chatSession")
    @JsonIgnore
    private List<Message> messages;
}