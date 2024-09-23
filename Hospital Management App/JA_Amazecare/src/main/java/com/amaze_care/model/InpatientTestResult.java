package com.amaze_care.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class InpatientTestResult {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    //@JoinColumn(name = "inpatient_test_id", nullable = false)
    private InPatientTest inPatientTest;

    @Column(nullable = false)
    private String fileName;
    
    @Column(length = 1000)
    private String filePath;

    private LocalDate uploadedAt;
    
    private String resultstatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InPatientTest getInPatientTest() {
		return inPatientTest;
	}

	public void setInPatientTest(InPatientTest inPatientTest) {
		this.inPatientTest = inPatientTest;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public LocalDate getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(LocalDate uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public String getResultstatus() {
		return resultstatus;
	}

	public void setResultstatus(String resultstatus) {
		this.resultstatus = resultstatus;
	}

}
