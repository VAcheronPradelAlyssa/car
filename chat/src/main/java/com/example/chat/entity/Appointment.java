package com.example.chat.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "APPOINTMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private CustomerService advisor;

    private java.time.LocalDateTime date;

    @Column(length = 20)
    private String status;
}