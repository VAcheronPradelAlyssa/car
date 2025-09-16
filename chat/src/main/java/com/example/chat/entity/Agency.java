package com.example.chat.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "AGENCY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 200)
    private String address;

    @OneToMany(mappedBy = "agency")
    @JsonIgnore
    private List<Car> cars;
}