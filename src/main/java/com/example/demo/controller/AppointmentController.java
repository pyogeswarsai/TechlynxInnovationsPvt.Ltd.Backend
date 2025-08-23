package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/appointment")
    public ResponseEntity<String> saveAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment saved = appointmentService.saveAppointment(appointment);

            // ✅ Email triggers
            emailService.sendToHRAppointment(saved);
            emailService.sendToUserAppointment(saved);

            return ResponseEntity.ok("✅ Appointment booked successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error booking appointment: " + e.getMessage());
        }
    }
}