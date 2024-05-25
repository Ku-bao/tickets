package com.example.tickets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tickets.model.User;
import com.example.tickets.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void insertUser(User user) {
        userRepository.insertUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber());
    }

    @Transactional
    public void updateUserPassword(String email, String newPassword) {
        userRepository.updateUserPassword(email, newPassword);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
