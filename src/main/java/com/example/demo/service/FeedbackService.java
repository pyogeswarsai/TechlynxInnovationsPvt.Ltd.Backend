package com.example.demo.service;

import com.example.demo.entity.Feedback;
import com.example.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepo;

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }
}
