package com.example.chat.entity;


import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToOne
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    private String status; // ACTIVE, BUSY, OFFLINE

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Message> sentMessages;
    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Message> receivedMessages;
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<ChatSession> clientSessions;
    @OneToMany(mappedBy = "advisor")
    @JsonIgnore
    private List<ChatSession> advisorSessions;
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Appointment> appointments;
}
