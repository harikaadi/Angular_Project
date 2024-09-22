package com.amaze_care.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amaze_care.model.Doctor;
import com.amaze_care.model.HealthIssue;
import com.amaze_care.model.UserInfo;
import com.amaze_care.repo.DoctorRepository;
import com.amaze_care.repo.HealthIssueRepository;
import com.amaze_care.repo.UserRepository;

@Service
public class HealthIssueService {
	
	@Autowired
	private HealthIssueRepository healthissuerepository;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private DoctorRepository doctorrepo;

	public HealthIssue addHealthIssue(HealthIssue healthissue) {
		
		//deattch the doctor from healthissue 
		Doctor doctor = healthissue.getDoctor();
		
		// deattach the user from doctor
		UserInfo user = doctor.getUser();
		
		//setting role to doctor and encrypt the password
		user.setRole("ROLE_DOCTOR");
		user.setPassword(passwordencoder.encode(user.getPassword()));
		
		//saving the user
		userrepo.save(user);
		
		//aving the doctor
		doctorrepo.save(doctor);
		
		//saving the healthissue
		return healthissuerepository.save(healthissue);
		//return healthissuerepo.save(healthissue);
		// TODO Auto-generated method stub
	
	}

}
