package com.amaze_care.model;


import com.amaze_care.enums.CommonHealthIssues;
import com.amaze_care.enums.DoctorType;
import com.amaze_care.enums.Specialization;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	private String name;
	
	 @Enumerated(EnumType.STRING)
	private DoctorType doctortype;//doctorType
    
	@Enumerated(EnumType.STRING)
    private Specialization specialization;
	
	private int experience;
	
	private String qualification;
	
	private String designation;
	
	private Boolean available;

  
    @OneToOne
    private UserInfo user;
    
    @Enumerated(EnumType.STRING)
    private CommonHealthIssues hissue;


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public DoctorType getDoctortype() {
		return doctortype;
	}


	public void setDoctortype(DoctorType doctortype) {
		this.doctortype = doctortype;
	}


	public Specialization getSpecialization() {
		return specialization;
	}


	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}


	public int getExperience() {
		return experience;
	}


	public void setExperience(int experience) {
		this.experience = experience;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public Boolean getAvailable() {
		return available;
	}


	public void setAvailable(Boolean available) {
		this.available = available;
	}


	public UserInfo getUser() {
		return user;
	}


	public void setUser(UserInfo user) {
		this.user = user;
	}


	
    
}
