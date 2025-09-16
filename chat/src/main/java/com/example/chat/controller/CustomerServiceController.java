package com.example.chat.controller;

import com.example.chat.entity.CustomerService;
import com.example.chat.service.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer-services")
public class CustomerServiceController {
    @Autowired
    private CustomerServiceService customerServiceService;

    @GetMapping
    public List<CustomerService> getAllCustomerServices() {
        return customerServiceService.getAllCustomerServices();
    }

    @GetMapping("/{id}")
    public Optional<CustomerService> getCustomerServiceById(@PathVariable Long id) {
        return customerServiceService.getCustomerServiceById(id);
    }

    @PostMapping
    public CustomerService createCustomerService(@RequestBody CustomerService customerService) {
        return customerServiceService.saveCustomerService(customerService);
    }

    @PutMapping("/{id}")
    public CustomerService updateCustomerService(@PathVariable Long id, @RequestBody CustomerService customerService) {
        customerService.setId(id);
        return customerServiceService.saveCustomerService(customerService);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerService(@PathVariable Long id) {
        customerServiceService.deleteCustomerService(id);
    }

    @PutMapping("/{id}/status")
    public CustomerService updateCustomerServiceStatus(@PathVariable Long id, @RequestParam String status) {
        return customerServiceService.updateCustomerServiceStatus(id, status);
    }
}
