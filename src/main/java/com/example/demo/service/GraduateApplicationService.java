package com.example.demo.service;

import com.example.demo.entity.EducationDetail;
import com.example.demo.entity.GraduateApplication;
import com.example.demo.repository.GraduateApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GraduateApplicationService {
	@Autowired
    private GraduateApplicationRepository applicationRepository;

    public GraduateApplicationService(GraduateApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public GraduateApplication saveApplication(GraduateApplication application, MultipartFile resumeFile) throws IOException {
        // Save resume file to /uploads/resumes
        if (resumeFile != null && !resumeFile.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads/resumes/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + resumeFile.getOriginalFilename();
            String filePath = uploadDir + fileName;
            
            resumeFile.transferTo(new File(filePath));

            application.setResumePath(filePath);
        }
        
        if (application.getEducationDetails() != null) {
        	for(EducationDetail detail : application.getEducationDetails()) {
        		detail.setGraduate(application);
        	}
        }
        // Save GraduateApplication (with educationDetails cascade)
        return applicationRepository.save(application);
    }

    public List<GraduateApplication> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Optional<GraduateApplication> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }
}
