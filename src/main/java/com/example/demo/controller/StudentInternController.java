package com.example.demo.controller;

import com.example.demo.entity.StudentInternApplication;
import com.example.demo.repository.StudentInternRepository;
import com.example.demo.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/careers/intern")
@CrossOrigin(origins = "http://localhost:5173")  // React frontend URL
public class StudentInternController {

    @Autowired
    private StudentInternRepository repository;
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/apply")
    public ResponseEntity<String> applyIntern(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String collegeName,
            @RequestParam String branch,
            @RequestParam String yearOfStudy,
            @RequestParam Double cgpa,
            @RequestParam("resume") MultipartFile resume,
            @RequestParam String motivation
    ) {
        try {
            // ✅ Absolute upload directory inside project folder
            String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            // ✅ Safe filename (avoid spaces and duplicates)
            String safeFileName = System.currentTimeMillis() + "_" +
                    resume.getOriginalFilename().replaceAll("\\s+", "_");

            // ✅ Final file path
            String filePath = uploadDir + File.separator + safeFileName;

            // ✅ Save file
            resume.transferTo(new File(filePath));

            // ✅ Save application details in DB
            StudentInternApplication app = new StudentInternApplication(
                    firstName, lastName, email, phoneNumber,
                    collegeName, branch, yearOfStudy, cgpa,
                    filePath, motivation
            );
            repository.save(app);
            
            emailService.sendToHRIntern(app);
            emailService.sendToApplicantIntern(app);

            return ResponseEntity.ok("Application submitted successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // log error in console
            return ResponseEntity.status(500).body("Error while uploading file: " + e.getMessage());
        }
    }
    @GetMapping("/resume/{id}")
    public ResponseEntity<?> downloadResume(@PathVariable Integer id) {
        try {
            // ✅ Fetch application from DB
            StudentInternApplication app = repository.findById(id).orElse(null);
            if (app == null) {
                return ResponseEntity.status(404).body("Application not found");
            }

            File file = new File(app.getResumePath());
            if (!file.exists()) {
                return ResponseEntity.status(404).body("File not found on server");
            }

            // ✅ Return file as download
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + file.getName())
                    .body(org.springframework.util.FileCopyUtils.copyToByteArray(file));

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error while downloading file: " + e.getMessage());
        }
    }

}
