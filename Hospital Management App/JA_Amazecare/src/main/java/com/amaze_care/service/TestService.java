package com.amaze_care.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaze_care.model.Test;
import com.amaze_care.repo.TestRepository;

@Service
public class TestService {
	
	@Autowired
	private TestRepository testrepository;
	
	public Test addTest(Test testinp) {
		return testrepository.save(testinp);
		
	}

}
