package com.example.Loginapp.controller;

import com.example.Loginapp.model.User;
import com.example.Loginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
private PasswordEncoder passwordEncoder;

    // Register user
    @PostMapping("/register")
public String registerUser(@RequestBody User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword())); // 🔥 important
    userRepository.save(user);
    return "User registered successfully";
}
}