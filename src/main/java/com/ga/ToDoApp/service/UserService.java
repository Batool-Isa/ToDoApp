package com.ga.ToDoApp.service;

import com.ga.ToDoApp.exception.InformationExistException;
import com.ga.ToDoApp.model.User;
import com.ga.ToDoApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User userObj) {
        System.out.println("Service calling create user!!");
        if (!userRepository.existsByEmailAddress(userObj.getEmailAddress())) {
            userObj.setPassword(passwordEncoder.encode(userObj.getPassword()));
            return userRepository.save(userObj);
        } else {
            throw new InformationExistException("User with email address " + userObj.getEmailAddress() + " already exist!");
        }
    }

    public User findEmailByEmailAddress(String email) {
        System.out.println("Service calling find user by email address!!");
        return userRepository.findByEmailAddress(email);
    }
}
