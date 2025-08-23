package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_intern_applications")
public class StudentInternApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first_name", nullable=false, length=50)
    private String firstName;

    @Column(name="last_name", nullable=false, length=50)
    private String lastName;

    @Column(nullable=false, length=100)
    private String email;

    @Column(name="phone_number", nullable=false, length=20)
    private String phoneNumber;

    @Column(name="college_name", nullable=false, length=150)
    private String collegeName;

    @Column(nullable=false, length=100)
    private String branch;

    @Column(name="year_of_study", nullable=false, length=20)
    private String yearOfStudy;

    private Double cgpa;

    @Column(name="resume_path", nullable=false, length=255)
    private String resumePath;

    private String motivation;
    
    public StudentInternApplication() {
		// TODO Auto-generated constructor stub
	}
    

	public StudentInternApplication(String firstName, String lastName, String email, String phoneNumber,
			String collegeName, String branch, String yearOfStudy, Double cgpa, String resumePath, String motivation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.collegeName = collegeName;
		this.branch = branch;
		this.yearOfStudy = yearOfStudy;
		this.cgpa = cgpa;
		this.resumePath = resumePath;
		this.motivation = motivation;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(String yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Double getCgpa() {
		return cgpa;
	}

	public void setCgpa(Double cgpa) {
		this.cgpa = cgpa;
	}

	public String getResumePath() {
		return resumePath;
	}

	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
    
}
