package com.example.demo.controller;

import com.example.demo.entity.RequestService;
import com.example.demo.service.EmailService;
import com.example.demo.service.RequestServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class RequestServiceController {

    @Autowired
    private RequestServiceService requestService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/request")
    public ResponseEntity<String> saveRequest(@RequestBody RequestService req) {
        try {
            RequestService saved = requestService.saveRequest(req);

            // ✅ Email triggers
            emailService.sendToHRServiceRequest(saved);
            emailService.sendToApplicantServiceRequest(saved);

            return ResponseEntity.ok("✅ Service request submitted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error submitting request: " + e.getMessage());
        }
    }
}