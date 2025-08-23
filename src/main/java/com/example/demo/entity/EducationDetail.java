package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "graduate_education_details")
public class EducationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degree;
    private String institution;
    private Integer year;
    private Double cgpa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graduate_id")
    @JsonIgnoreProperties("educationDetails")
    private GraduateApplication graduate;
    public EducationDetail() {
		// TODO Auto-generated constructor stub
	}
	public EducationDetail(Long id, String degree, String institution, Integer year, Double cgpa,
			GraduateApplication graduate) {
		super();
		this.id = id;
		this.degree = degree;
		this.institution = institution;
		this.year = year;
		this.cgpa = cgpa;
		this.graduate = graduate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getCgpa() {
		return cgpa;
	}
	public void setCgpa(Double cgpa) {
		this.cgpa = cgpa;
	}
	public GraduateApplication getGraduate() {
		return graduate;
	}
	public void setGraduate(GraduateApplication graduate) {
		this.graduate = graduate;
	}
    
}
