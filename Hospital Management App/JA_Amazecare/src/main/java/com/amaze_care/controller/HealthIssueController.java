package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.model.HealthIssue;
import com.amaze_care.service.HealthIssueService;



@RestController
public class HealthIssueController {
	
	@Autowired
	private HealthIssueService healthissueservice;
	
	@PostMapping("/healthissue/add")
	public ResponseEntity<HealthIssue> addHealthIssue(@RequestBody HealthIssue healthissue) {
		System.out.println("inside /healthissue/add");
		HealthIssue savedhealthissue = healthissueservice.addHealthIssue(healthissue);
		
		return ResponseEntity.ok(savedhealthissue ) ;
		
		
	}


}
