package com.amaze_care.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amaze_care.model.InPatient;
import com.amaze_care.model.UserInfo;
import com.amaze_care.repo.InPatientRepository;
import com.amaze_care.repo.UserRepository;

@Service
public class InPatientService {
	
	@Autowired
	private InPatientRepository inpatientrepository;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private UserRepository userrepositary;

	public InPatient addPatient(InPatient patient) {
		// TODO Auto-generated method stub
		System.out.println("in addPatinet");
		System.out.println(patient);
		
		       //detach user info from patient
				UserInfo user=patient.getUser();				
				System.out.println(user);
				
				
				user.setRole("ROLE_PATIENT");
				
				user.setPassword(passwordencoder.encode(user.getPassword()));
				
				user = userrepositary.save(user);
				
				//attaching user to employee
				patient.setUser(user);
		
				return inpatientrepository.save(patient);
						
	}

}
