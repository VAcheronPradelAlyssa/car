package com.example.chat.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RENTAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private java.time.LocalDateTime startDate;
    private java.time.LocalDateTime endDate;

    private BigDecimal totalPrice;
}