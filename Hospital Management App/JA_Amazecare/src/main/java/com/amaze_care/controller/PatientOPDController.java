package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.model.PatientOPD;
import com.amaze_care.service.PatientOPDService;

@RestController
public class PatientOPDController {
	
	@Autowired
	private PatientOPDService patientOPDService;
	
	@PostMapping("/patient-opd/add")
	public ResponseEntity<?> addPatient(@RequestBody PatientOPD patientOPD, MessageDto messageDto) {
	    try {
	        // Call the service to add the patient OPD details
	        PatientOPD savedPatientOPD = patientOPDService.addPatient(patientOPD);
	        return ResponseEntity.ok(savedPatientOPD);  // Return the saved PatientOPD object
	    } catch (Exception e) {
	        // Set the error message in the MessageDto
	        messageDto.setMsg(e.getMessage());
	        return ResponseEntity.badRequest().body(messageDto);  // Return the error message in the response body
	    }
	}


}
