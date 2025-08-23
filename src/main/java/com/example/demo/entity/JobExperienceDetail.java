package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_experience_details")
public class JobExperienceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String rolePosition;
    private Double yearsOfExperience;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private JobApplication application;

    // Getters and Setters
    public JobExperienceDetail() {
		// TODO Auto-generated constructor stub
	}

	public JobExperienceDetail(Long id, String companyName, String rolePosition, Double yearsOfExperience,
			JobApplication application) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.rolePosition = rolePosition;
		this.yearsOfExperience = yearsOfExperience;
		this.application = application;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRolePosition() {
		return rolePosition;
	}

	public void setRolePosition(String rolePosition) {
		this.rolePosition = rolePosition;
	}

	public Double getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Double yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public JobApplication getApplication() {
		return application;
	}

	public void setApplication(JobApplication application) {
		this.application = application;
	}
    
}