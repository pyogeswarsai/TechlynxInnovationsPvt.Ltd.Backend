package com.example.demo.controller;

import com.example.demo.entity.Feedback;
import com.example.demo.service.EmailService;
import com.example.demo.service.FeedbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "https://techlynxinnovationspvtltd.netlify.app")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/feedback")
    public ResponseEntity<String> saveFeedback(@RequestBody Feedback feedback) {
        try {
            Feedback saved = feedbackService.saveFeedback(feedback);

            // ✅ Email triggers
            emailService.sendToHRFeedback(saved);
            emailService.sendToUserFeedback(saved);

            return ResponseEntity.ok("✅ Feedback submitted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error submitting feedback: " + e.getMessage());
        }
    }
}