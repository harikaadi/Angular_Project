package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.model.PatientOPD;
import com.amaze_care.service.PatientOPDService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class PatientOPDController {
	
	@Autowired
	private PatientOPDService patientOPDService;
	
	@PostMapping("/patient-opd/add")
	public ResponseEntity<?> addPatient(@RequestBody PatientOPD patientOPD, MessageDto messageDto) {
	    try {
	        
	        PatientOPD savedPatientOPD = patientOPDService.addPatient(patientOPD);
	        return ResponseEntity.ok(savedPatientOPD); 
	    } catch (Exception e) {
	        messageDto.setMsg(e.getMessage());
	        return ResponseEntity.badRequest().body(messageDto); 
	    }
	}


}
