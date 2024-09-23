package com.amaze_care.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amaze_care.model.Executive;
import com.amaze_care.model.UserInfo;
import com.amaze_care.repo.ExecutiveRepository;
import com.amaze_care.repo.UserRepository;

@Service
public class ExecutiveService {
	
	@Autowired
	private ExecutiveRepository executiveRepository;
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

	public Executive addexecutive(Executive executive) {
		// Detach user info from doctor
        UserInfo user = executive.getUser();
        System.out.println(user);
        user.setRole("ROLE_EXECUTIVE");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save UserInfo first
        user = userRepository.save(user);

        // Attach the saved user back to doctor
        executive.setUser(user);
		
		
		
		return executiveRepository.save(executive);
	}
	

}
