package com.amaze_care.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "InPatient_Tests")
public class InPatientTest {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@ManyToOne
	private InPatient inpatient;
	//patientid
	
	//testid
	@ManyToOne
	private Test test;
	
	//doctorid
	@ManyToOne
	private 	Doctor doctor;
	
	
	private LocalDate Date;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public InPatient getInpatient() {
		return inpatient;
	}


	public void setInpatient(InPatient inpatient) {
		this.inpatient = inpatient;
	}


	public Test getTest() {
		return test;
	}


	public void setTest(Test test) {
		this.test = test;
	}


	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public LocalDate getDate() {
		return Date;
	}


	public void setDate(LocalDate date) {
		Date = date;
	}

}
