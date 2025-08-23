package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String mobile;
    private String college;
    private Integer graduationYear;
    private String roleApplied;
    private String preferredLocation;
    private String resumePath;
    private String skills;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobExperienceDetail> experienceDetails = new ArrayList<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobEducationDetail> educationDetails = new ArrayList<>();

    // Getters and Setters
    public JobApplication() {
		// TODO Auto-generated constructor stub
	}

	public JobApplication(Long id, String firstName, String lastName, String gender, String email, String mobile,
			String college, Integer graduationYear, String roleApplied, String preferredLocation, String resumePath,
			String skills, List<JobExperienceDetail> experienceDetails, List<JobEducationDetail> educationDetails) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
		this.college = college;
		this.graduationYear = graduationYear;
		this.roleApplied = roleApplied;
		this.preferredLocation = preferredLocation;
		this.resumePath = resumePath;
		this.skills = skills;
		this.experienceDetails = experienceDetails;
		this.educationDetails = educationDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public Integer getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(Integer graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getRoleApplied() {
		return roleApplied;
	}

	public void setRoleApplied(String roleApplied) {
		this.roleApplied = roleApplied;
	}

	public String getPreferredLocation() {
		return preferredLocation;
	}

	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	public String getResumePath() {
		return resumePath;
	}

	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public List<JobExperienceDetail> getExperienceDetails() {
		return experienceDetails;
	}

	public void setExperienceDetails(List<JobExperienceDetail> experienceDetails) {
		this.experienceDetails = experienceDetails;
	}

	public List<JobEducationDetail> getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(List<JobEducationDetail> educationDetails) {
		this.educationDetails = educationDetails;
	}
    
}
