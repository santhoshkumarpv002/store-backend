package com.example.store_backend.service;

import com.example.store_backend.entity.User;
import com.example.store_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        logger.info("Fetching user with id: {}", id);
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        logger.info("Creating user: {}", user.getUsername());
        user.setId(null); // Ensure new user is always inserted
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        logger.info("Updating user with id: {}", id);
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            logger.info("User updated: {}", user.getUsername());
            return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }
}
