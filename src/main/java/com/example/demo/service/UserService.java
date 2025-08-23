package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignupRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    
    public User createUser(SignupRequest request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = new User(request.getName(), request.getEmail(), hashedPassword);
        return userRepository.save(newUser);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> authenticate (String email, String password) {
    	Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public String registerUser(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered";
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return "Passwords do not match";
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = new User(request.getName(), request.getEmail(), hashedPassword);
        userRepository.save(newUser);
        return "Registration successful";
    }

    public String loginUser(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return "User not found";
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Invalid credentials";
        }
        return "Login successful";
    }
    
//    public Optional<User> authenticate(String email, String password) {
//        return userRepository.findByEmail(email)
//                .filter(user -> user.getPassword().equals(password));
//    }

}
