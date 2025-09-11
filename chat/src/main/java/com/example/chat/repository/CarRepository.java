package com.example.chat.repository;

import com.example.chat.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByAgencyId(Long agencyId);
    List<Car> findByCategoryId(Long categoryId);
}
