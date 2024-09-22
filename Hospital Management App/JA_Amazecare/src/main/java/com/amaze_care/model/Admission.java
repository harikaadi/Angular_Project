package com.amaze_care.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Admission {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	// The patient being admitted
    @ManyToOne
    private InPatient inpatient; 
    
    // The doctor assigned to the patients
    @ManyToOne
    private Doctor doctor; 

    // The room allocated to the patient
    @ManyToOne
    private Room room; 
    
    // e.g., General, Surgery
    private String admissionType; 
    
    // Date of admission
    private LocalDate admissionDate; 
    
    private String admissionStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InPatient getInpatient() {
		return inpatient;
	}

	public void setInpatient(InPatient inpatient) {
		this.inpatient = inpatient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getAdmissionType() {
		return admissionType;
	}

	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getAdmissionStatus() {
		return admissionStatus;
	}

	public void setAdmissionStatus(String admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

}
