package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "graduate_applications")
public class GraduateApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String college;
    private Integer graduationYear;
    private Double cgpa;
    private String gender;

    // Store only file path of uploaded resume
    private String resumePath;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @Column(columnDefinition = "TEXT")
    private String motivation;

    @OneToMany(mappedBy = "graduate", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("graduates")
    private List<EducationDetail> educationDetails;
    
    public GraduateApplication() {
		// TODO Auto-generated constructor stub
	}

	public GraduateApplication(String firstName, String lastName, String email, String phone, String college,
			Integer graduationYear, Double cgpa, String gender, String resumePath, String skills, String motivation,
			List<EducationDetail> educationDetails) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.college = college;
		this.graduationYear = graduationYear;
		this.cgpa = cgpa;
		this.gender = gender;
		this.resumePath = resumePath;
		this.skills = skills;
		this.motivation = motivation;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Double getCgpa() {
		return cgpa;
	}

	public void setCgpa(Double cgpa) {
		this.cgpa = cgpa;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

	public List<EducationDetail> getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(List<EducationDetail> educationDetails) {
		this.educationDetails = educationDetails;
	}
	
    
}
