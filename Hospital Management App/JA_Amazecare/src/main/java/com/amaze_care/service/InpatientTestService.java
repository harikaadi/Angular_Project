package com.amaze_care.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.Doctor;
import com.amaze_care.model.InPatient;
import com.amaze_care.model.InPatientTest;
import com.amaze_care.model.Test;
import com.amaze_care.repo.DoctorRepository;
import com.amaze_care.repo.InPatientRepository;
import com.amaze_care.repo.InpatientTestRepository;
import com.amaze_care.repo.TestRepository;

@Service
public class InpatientTestService {
	
	@Autowired
	private InpatientTestRepository inpatienttestrepo ;
	@Autowired
	private InPatientRepository inpatientrepo;
	@Autowired
	private DoctorRepository doctorrepo;
	@Autowired
	private TestRepository testrepo;
	
	public InPatientTest AssignTesttoPatient(int patientid, int doctorid, int testid) throws InvalidIdException {
		
		
		 Optional<InPatient>optionalpatient = inpatientrepo.findById(patientid);
		 if(optionalpatient.isEmpty()) {
			throw new  InvalidIdException("Invalid patient id");
		 }
		 InPatient inpatient = optionalpatient.get();
		 
		 Optional<Doctor>optionaldoctor = doctorrepo.findById(doctorid);
		 if(optionaldoctor.isEmpty()) {
			throw new  InvalidIdException("Invalid doctor id");
		 }
		 Doctor doctor = optionaldoctor .get();
		 
		 
		 Optional<Test>optionaltest = testrepo.findById(testid);
		 if(optionaltest.isEmpty()) {
			throw new  InvalidIdException("Invalid patient id");
		 }
		Test test = optionaltest.get();
		
		InPatientTest inpatienttest = new InPatientTest();
		
		inpatienttest.setInpatient(inpatient);
		inpatienttest.setDoctor(doctor);
		inpatienttest.setTest(test);
		inpatienttest.setDate(LocalDate.now());
		
		return inpatienttestrepo.save(inpatienttest);
		
		
		
	}
}
