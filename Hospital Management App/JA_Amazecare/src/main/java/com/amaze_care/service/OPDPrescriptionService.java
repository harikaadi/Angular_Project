package com.amaze_care.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaze_care.exception.DoctorNotFoundException;
import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.Doctor;
import com.amaze_care.model.OPDPrescription;
import com.amaze_care.model.PatientOPD;
import com.amaze_care.repo.DoctorRepository;
import com.amaze_care.repo.OPDPrescriptionRepository;
import com.amaze_care.repo.PatientOPDRepository;

@Service
public class OPDPrescriptionService {
	
	@Autowired
	private OPDPrescriptionRepository opdPrescriptionRepository;
	
	@Autowired
	private PatientOPDRepository patientOPDRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	
	public OPDPrescription addPrescription(int opdPatientId, int doctorId, OPDPrescription prescription) throws InvalidIdException, DoctorNotFoundException {
	    System.out.println("Inside addPrescription service");

	    // Fetch patient by opdPatientId
	    Optional<PatientOPD> optionalPatient = patientOPDRepository.findById(opdPatientId);
	    if (optionalPatient.isEmpty()) {
	        throw new InvalidIdException("opdPatientId is invalid");
	    }
	    PatientOPD patient = optionalPatient.get();
	    System.out.println("Fetched Patient: " + patient);

	    // Fetch doctor by doctorId
	    Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
	    if (optionalDoctor.isEmpty()) {
	        throw new DoctorNotFoundException("doctorId is invalid");
	    }
	    Doctor doctor = optionalDoctor.get();
	    System.out.println("Fetched Doctor: " + doctor);

	    // Set patient and doctor in the prescription
	    prescription.setPatientOPD(patient);
	    prescription.setDoctor(doctor);

	    // Save the prescription to the database
	    return opdPrescriptionRepository.save(prescription);
	}


}
