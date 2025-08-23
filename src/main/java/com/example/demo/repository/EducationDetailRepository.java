package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.EducationDetail;

public interface EducationDetailRepository extends JpaRepository<EducationDetail, Long> {
}

