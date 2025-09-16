package com.example.chat.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CAR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(length = 50)
    private String brand;

    @Column(length = 50)
    private String model;

    private Integer year;

    @Column(length = 20)
    private String plate;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Offer> offers;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Rental> rentals;
}