package com.example.chat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
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
    private User advisor;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    @OneToMany(mappedBy = "chatSession")
    @JsonIgnore
    private List<Message> messages;
}
