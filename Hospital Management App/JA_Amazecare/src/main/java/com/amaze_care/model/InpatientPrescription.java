package com.amaze_care.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class InpatientPrescription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	private String medicineName;
	private String dosage;
	private String treatmentPlan;
	
	@ManyToOne
	private InPatient inpatient;
	
	@ManyToOne
	private Doctor doctor;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getTreatmentPlan() {
		return treatmentPlan;
	}

	public void setTreatmentPlan(String treatmentPlan) {
		this.treatmentPlan = treatmentPlan;
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

}
