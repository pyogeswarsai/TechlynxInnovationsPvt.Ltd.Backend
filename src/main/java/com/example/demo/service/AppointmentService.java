package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }
}
