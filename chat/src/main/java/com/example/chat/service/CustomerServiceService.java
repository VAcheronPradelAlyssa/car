
package com.example.chat.service;

import com.example.chat.entity.CustomerService;
import com.example.chat.repository.CustomerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceService {
    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    public CustomerService getByUsername(String username) {
        return customerServiceRepository.findByUsername(username).orElse(null);
    }

    public List<CustomerService> getAllCustomerServices() {
        return customerServiceRepository.findAll();
    }

    public Optional<CustomerService> getCustomerServiceById(Long id) {
        return customerServiceRepository.findById(id);
    }

    public CustomerService saveCustomerService(CustomerService customerService) {
        return customerServiceRepository.save(customerService);
    }

    public void deleteCustomerService(Long id) {
        customerServiceRepository.deleteById(id);
    }

    public CustomerService updateCustomerServiceStatus(Long id, String status) {
        Optional<CustomerService> optional = customerServiceRepository.findById(id);
        if (optional.isPresent()) {
            CustomerService cs = optional.get();
            cs.setStatus(status);
            return customerServiceRepository.save(cs);
        }
        return null;
    }
}
