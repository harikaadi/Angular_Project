package com.amaze_care.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "OPD_Tests")
public class OPDTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    private PatientOPD patientOPD;
    //patientOPDId
    
    //testId
    @ManyToOne
    private Test test;
    
    //doctorId
    @ManyToOne
    private Doctor doctor;
    
    private LocalDate date;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PatientOPD getPatientOPD() {
        return patientOPD;
    }

    public void setPatientOPD(PatientOPD patientOPD) {
        this.patientOPD = patientOPD;
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
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

