package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.model.InPatient;
import com.amaze_care.service.InPatientService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class InPatientController {
	
	@Autowired
	private InPatientService inpatientservice;

	@PostMapping("/inpatient/add")
	public ResponseEntity<InPatient> addPatient(@RequestBody InPatient patient) {
		System.out.println("in controller");
		
				InPatient savedPatient=inpatientservice.addPatient(patient);
				 return ResponseEntity.ok(savedPatient);
				 
	}
}
