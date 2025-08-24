package com.example.demo.controller;

import com.example.demo.entity.GraduateApplication;
import com.example.demo.service.EmailService;
import com.example.demo.service.GraduateApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/careers/graduates")
@CrossOrigin(origins = "https://techlynxinnovationspvtltd.netlify.app")
public class GraduateApplicationController {

    private final GraduateApplicationService applicationService;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    public GraduateApplicationController(GraduateApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // POST - Apply (with resume + multiple education details)
    @PostMapping(value = "/apply", consumes = "multipart/form-data")
    public ResponseEntity<GraduateApplication> saveApplication(
            @RequestPart("application") String applicationJson,
            @RequestPart("resume") MultipartFile resumeFile) throws IOException {

    	ObjectMapper objectMapper = new ObjectMapper();
        GraduateApplication application = objectMapper.readValue(applicationJson, GraduateApplication.class);

        GraduateApplication saved = applicationService.saveApplication(application, resumeFile);
        try {        
        	emailService.sendToHR(saved);
        	emailService.sendToApplicant(saved);
        } catch (Exception e) {
        	System.out.println("Email sending failed: " + e.getMessage());
        }
        return ResponseEntity.ok(saved);
    }

    // GET - All applications
    @GetMapping("/applications")
    public ResponseEntity<List<GraduateApplication>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    // GET - Single application by ID
    @GetMapping("/applications/{id}")
    public ResponseEntity<GraduateApplication> getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
