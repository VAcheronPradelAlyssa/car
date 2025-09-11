package com.example.chat.service;

import com.example.chat.entity.Car;
import com.example.chat.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCarsByAgency(Long agencyId) {
        return carRepository.findByAgencyId(agencyId);
    }

    public List<Car> getCarsByCategory(Long categoryId) {
        return carRepository.findByCategoryId(categoryId);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
