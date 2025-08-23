package com.example.demo.repository;

import com.example.demo.entity.StudentInternApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInternRepository extends JpaRepository<StudentInternApplication, Integer> {
}
