package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.ResetPasswordRequest;
import com.example.demo.dto.SignupRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtService;
import com.example.demo.service.PasswordResetService;
import com.example.demo.service.UserService;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://techlynxinnovationspvtltd.netlify.app")

public class UserController {

	private UserRepository userRepository;
    private  UserService userService;
	private JwtService jwtService;
	private PasswordResetService passwordResetService;

	@Autowired
	public UserController(UserRepository userRepository,
							UserService userService,
							JwtService jwtService,
							PasswordResetService passwordResetService) {
		this.userRepository = userRepository;
		this.userService = userService;
		this.jwtService = jwtService;
		this.passwordResetService = passwordResetService;
	}

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("success", false, "message", "Passwords do not match"));
        }
        if (userService.emailExists(req.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("success", false, "message", "Email already registered"));
        }
        User user = userService.createUser(req);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of("success", true, "id", user.getId(), "message", "Registered"));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Optional<User> user = userService.authenticate(req.getEmail(), req.getPassword());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("success", false, "message", "Invalid email or password"));
        }
        String token = jwtService.generateToken(user.get());
        return ResponseEntity.ok(Map.of("success", true, "token", token, "name", user.get().getName(), "email", user.get().getEmail()));
    }
    
    @PostMapping("/request-reset")
    public ResponseEntity<?> requestReset(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String token = passwordResetService.createPasswordResetToken(email);

        if (token == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
        }

        // In real app, send token via email
        System.out.println("Reset token for " + email + ": " + token);

        return ResponseEntity.ok("Password reset token sent to your email (check console in dev mode)");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
    	boolean success  = passwordResetService.resetPassword(request.getToken(), request.getNewPassword());
    	return success ? ResponseEntity.ok("Password updated successfully") : ResponseEntity.badRequest().body("Invalid token");
    }

}
