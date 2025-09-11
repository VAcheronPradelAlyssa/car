package com.example.chat.service;

import com.example.chat.entity.User;
import com.example.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getActiveAdvisors() {
        return userRepository.findByRole_NameAndStatus("ADVISOR", "ACTIVE");
    }

    public User updateUserStatus(Long userId, String status) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setStatus(status);
            return userRepository.save(user);
        }
        return null;
    }
}
