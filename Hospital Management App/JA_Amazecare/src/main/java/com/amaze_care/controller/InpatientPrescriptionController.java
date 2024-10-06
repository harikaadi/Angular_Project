package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.model.InpatientPrescription;
import com.amaze_care.service.InpatientPrescriptionService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class InpatientPrescriptionController {

	@Autowired
	private InpatientPrescriptionService inpatientPrescriptionService;
	
	@PostMapping("/inpatient-add-prescription/{InPatientId}/{doctorId}")
	public ResponseEntity<?> addPrescription(@PathVariable int InPatientId,@PathVariable int doctorId, @RequestBody InpatientPrescription prescription, MessageDto messageDto) {
	    try {
	    	InpatientPrescription savedPrescription = inpatientPrescriptionService.addPrescription(InPatientId,doctorId,prescription);
	        return ResponseEntity.ok(savedPrescription);
	    } catch (Exception e) {
	        messageDto.setMsg(e.getMessage());
	        return ResponseEntity.badRequest().body(messageDto);
	    }
	}

}
