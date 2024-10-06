package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.MedicalHistory;
import com.amaze_care.service.MedicalHistoryService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class MedicalHistoryController {

	@Autowired
	private MedicalHistoryService medicalHistoryService;
	
	@PostMapping("/patient/history/add/{pid}")
	public ResponseEntity<?> addPatientHistory(@PathVariable int pid, @RequestBody MedicalHistory medicalHistory) {
		try {
			
			medicalHistory =  medicalHistoryService.addPatientHistory(pid,medicalHistory);
			return ResponseEntity.ok(medicalHistory); 
			
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
}
