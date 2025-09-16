package com.example.chat.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "OFFER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private java.math.BigDecimal price;

    @OneToMany(mappedBy = "offer")
    @JsonIgnore
    private List<Rental> rentals;
}