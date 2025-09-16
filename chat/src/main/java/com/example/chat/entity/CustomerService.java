package com.example.chat.entity;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER_SERVICE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String status;

    // Relations
    @OneToMany(mappedBy = "advisor")
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "advisor")
    @JsonIgnore
    private List<ChatSession> chatSessions;

    @OneToMany(mappedBy = "receiverCustomerService")
    @JsonIgnore
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "senderCustomerService")
    @JsonIgnore
    private List<Message> sentMessages;
}