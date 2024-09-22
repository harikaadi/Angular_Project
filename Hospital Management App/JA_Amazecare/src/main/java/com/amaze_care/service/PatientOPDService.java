package com.amaze_care.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amaze_care.model.PatientDoctor;
import com.amaze_care.model.PatientOPD;
import com.amaze_care.model.UserInfo;
import com.amaze_care.repo.PatientOPDRepository;
import com.amaze_care.repo.UserRepository;
@Service
public class PatientOPDService {

	@Autowired
	private PatientOPDRepository patientOPDRepository;

	private Logger logger = LoggerFactory.getLogger(PatientOPDService.class);

	
	@Autowired
	private UserRepository userRepository;
 
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 
	 public PatientOPD addPatient(PatientOPD patientOPD) {
	        // Detach user info from doctor
	        UserInfo user = patientOPD.getUser();
	        System.out.println(user);
	        user.setRole("ROLE_DOCTOR");
	        user.setPassword(passwordEncoder.encode(user.getPassword()));

	        // Save UserInfo first
	        user = userRepository.save(user);

	        // Attach the saved user back to doctor
	        patientOPD.setUser(user);

	        return patientOPDRepository.save(patientOPD);
	    }


	

}
