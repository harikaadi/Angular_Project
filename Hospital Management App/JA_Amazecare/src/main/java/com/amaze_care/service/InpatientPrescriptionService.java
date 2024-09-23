package com.amaze_care.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaze_care.exception.DoctorNotFoundException;
import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.Doctor;
import com.amaze_care.model.InPatient;
import com.amaze_care.model.InpatientPrescription;
import com.amaze_care.model.OPDPrescription;
import com.amaze_care.model.PatientOPD;
import com.amaze_care.repo.DoctorRepository;
import com.amaze_care.repo.InPatientRepository;
import com.amaze_care.repo.InpatientPrescriptionRepository;

@Service
public class InpatientPrescriptionService {
	
	@Autowired
	private InpatientPrescriptionRepository inpatientPrescriptionRepository;
	@Autowired
	private DoctorRepository  doctorRepository;
	@Autowired
	private InPatientRepository inpatientrepository;
	
	
	public InpatientPrescription addPrescription(int InPatientId, int doctorId, InpatientPrescription prescription) throws InvalidIdException, DoctorNotFoundException {
	    System.out.println("Inside addPrescription service");

	    // Fetch patient by opdPatientId
	    Optional<InPatient> optionalPatient = inpatientrepository.findById(InPatientId);
	    if (optionalPatient.isEmpty()) {
	        throw new InvalidIdException("opdPatientId is invalid");
	    }
	    InPatient patient = optionalPatient.get();
	    System.out.println("Fetched Patient: " + patient);

	    // Fetch doctor by doctorId
	    Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
	    if (optionalDoctor.isEmpty()) {
	        throw new DoctorNotFoundException("doctorId is invalid");
	    }
	    Doctor doctor = optionalDoctor.get();
	    System.out.println("Fetched Doctor: " + doctor);

	    // Set patient and doctor in the prescription
	    prescription.setInpatient(patient);
	    prescription.setDoctor(doctor);

	    // Save the prescription to the database
	    return inpatientPrescriptionRepository.save(prescription);
	}
	
	

}
