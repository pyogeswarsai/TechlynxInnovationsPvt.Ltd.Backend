package com.example.demo.service;

import com.example.demo.entity.StudentInternApplication;
import com.example.demo.repository.StudentInternRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentInternService {
    
    private final StudentInternRepository repository;

    public StudentInternService(StudentInternRepository repository) {
        this.repository = repository;
    }

    public StudentInternApplication saveApplication(StudentInternApplication app) {
        return repository.save(app);
    }
}
