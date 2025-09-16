package com.example.chat.repository;

import com.example.chat.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByCarId(Long carId);
    List<Rental> findByClient_Id(Long clientId);
}
