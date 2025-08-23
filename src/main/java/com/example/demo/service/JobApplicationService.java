package com.example.demo.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.JobApplication;
import com.example.demo.entity.JobEducationDetail;
import com.example.demo.entity.JobExperienceDetail;
import com.example.demo.repository.JobApplicationRepository;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository applicationRepo;
    
    public JobApplicationService(JobApplicationRepository applicationRepo) {
    	this.applicationRepo = applicationRepo;
    }

    public JobApplication saveApplication(JobApplication application, MultipartFile resumeFile) throws IOException {
        
    	if(resumeFile != null && !resumeFile.isEmpty()) {
    		String uploadDir = System.getProperty("user.dir") + "/uploads/resumes/";
    		File dir = new File(uploadDir);
    		if (!dir.exists()) dir.mkdirs();
    	
    	
    		String fileName = System.currentTimeMillis() + "_" + resumeFile.getOriginalFilename();
    		String filePath = uploadDir + fileName;

    		resumeFile.transferTo(new File(filePath));
    		application.setResumePath(filePath);
    	}
    	
    	if (application.getExperienceDetails() != null) {
    		for (JobExperienceDetail exp : application.getExperienceDetails()) {
    			exp.setApplication(application);
    		}
    	}
    	if (application.getCollege() != null) {
    		for (JobEducationDetail edu : application.getEducationDetails()) {
    			edu.setApplication(application);
    		}
    	}	
        return applicationRepo.save(application);
    }
}