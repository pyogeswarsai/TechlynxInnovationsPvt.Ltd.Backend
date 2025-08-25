package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;
    

    @Autowired
    public PasswordResetService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createPasswordResetToken(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return null; // Email not found
        }

        User user = userOpt.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(15)); // expires in 15 min
        userRepository.save(user);

        // send reset email
        sendPasswordResetEmail(user.getEmail(), token);

        return token;
    }

    private void sendPasswordResetEmail(String toEmail, String token) {
        String resetUrl = "https://techlynxinnovationspvtltd.netlify.app/reset-password?token=" + token; 
        // change this to your frontend URL

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info.techlynxinnovations@gmail.com"); // must match spring.mail.username
        message.setTo(toEmail);
        message.setSubject("Password Reset Request - Techlynx Innovations Pvt. Ltd");
        message.setText(
            "Hello,\n\n" +
            "We received a request to reset your password. Please click the link below:\n" +
            resetUrl + "\n\n" +
            "This link will expire in 15 minutes.\n\n" +
            "If you didn’t request this, you can ignore this email.\n\n" +
            "Thanks,\nTechlynx Innovations Pvt. Ltd."
        );

        mailSender.send(message);
    }

    public boolean resetPassword(String token, String newPassword) {
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        Optional<User> userOpt = userRepository.findByResetToken(token);
        if (userOpt.isEmpty()) {
            return false; // Invalid token
        }

        User user = userOpt.get();
        if (user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            return false; // Token expired
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);

        return true;
    }
}
