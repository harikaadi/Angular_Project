package com.amaze_care.model;

import com.amaze_care.enums.CommonHealthIssues;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class HealthIssue {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private CommonHealthIssues issueName; // The health issue (e.g., Chest Pain, Fever)

    @ManyToOne
    private Doctor doctor; // Doctor specializing in this issue

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CommonHealthIssues getIssueName() {
		return issueName;
	}

	public void setIssueName(CommonHealthIssues issueName) {
		this.issueName = issueName;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	

}
