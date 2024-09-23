package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.InPatientTest;
import com.amaze_care.service.InpatientTestService;

@RestController
public class InpatientTestController {
	@Autowired
	private InpatientTestService inpatienttestservice;
	

	@PostMapping("/inpatient-test/add/{patientid}/{doctorid}/{testid}")
	public ResponseEntity<?> AssignTesttoPatient(@PathVariable int patientid,@PathVariable int doctorid,@PathVariable int testid,MessageDto messagedto) {
		
		try {
			InPatientTest inpatienttest = inpatienttestservice.AssignTesttoPatient(patientid,doctorid,testid);
			return ResponseEntity.ok(inpatienttest);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			messagedto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(messagedto);
		}
		
	}
	

}
