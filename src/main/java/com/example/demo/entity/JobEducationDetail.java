package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_education_details")
public class JobEducationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String institutionName;
    private String degreeQualification;
    private Integer graduationYear;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private JobApplication application;

    public JobEducationDetail() {
		// TODO Auto-generated constructor stub
	}

	public JobEducationDetail(Long id, String institutionName, String degreeQualification, Integer graduationYear,
			JobApplication application) {
		super();
		this.id = id;
		this.institutionName = institutionName;
		this.degreeQualification = degreeQualification;
		this.graduationYear = graduationYear;
		this.application = application;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getDegreeQualification() {
		return degreeQualification;
	}

	public void setDegreeQualification(String degreeQualification) {
		this.degreeQualification = degreeQualification;
	}

	public Integer getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(Integer graduationYear) {
		this.graduationYear = graduationYear;
	}

	public JobApplication getApplication() {
		return application;
	}

	public void setApplication(JobApplication application) {
		this.application = application;
	}
    
}