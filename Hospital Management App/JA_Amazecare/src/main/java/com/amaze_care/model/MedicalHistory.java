package com.amaze_care.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
	public class MedicalHistory {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		
		private String medicalCondition; 
		
		private String sinceWhen; 
		
		
		
		@ManyToOne
		private InPatient inPatient;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		 
		public String getMedicalCondition() {
			return medicalCondition;
		}

		public void setMedicalCondition(String medicalCondition) {
			this.medicalCondition = medicalCondition;
		}

		public String getSinceWhen() {
			return sinceWhen;
		}

		public void setSinceWhen(String sinceWhen) {
			this.sinceWhen = sinceWhen;
		}

		

		public InPatient getInPatient() {
			return inPatient;
		}

		public void setInPatient(InPatient inPatient) {
			this.inPatient = inPatient;
		}
		
		
	}


