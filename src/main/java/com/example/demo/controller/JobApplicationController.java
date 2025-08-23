package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.JobApplication;
import com.example.demo.service.EmailService;
import com.example.demo.service.JobApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/careers/experienced/job-applications")
public class JobApplicationController {

    private final JobApplicationService applicationService;
    
    @Autowired
    private EmailService emailService;
    
    public JobApplicationController(JobApplicationService applicationService) {
    	this.applicationService = applicationService;
    }

    @PostMapping(value = "/apply", consumes = "multipart/form-data")
    public ResponseEntity<JobApplication> submitApplication(
            @RequestPart("application") String applicationJson,
            @RequestPart("resume") MultipartFile resumeFile) throws IOException{

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JobApplication application = objectMapper.readValue(applicationJson, JobApplication.class);

            JobApplication saved = applicationService.saveApplication(application, resumeFile);

            emailService.sendToHRExperienced(saved);
            emailService.sendToApplicantExperienced(saved);

            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}