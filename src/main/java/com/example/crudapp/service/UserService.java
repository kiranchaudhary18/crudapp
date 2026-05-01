package com.example.crudapp.service;

import com.example.crudapp.entity.User;
import com.example.crudapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get user by id
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Create a new user
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Update existing user
     */
    public Optional<User> updateUser(Long id, User userDetails) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    /**
     * Delete user by id
     */
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
