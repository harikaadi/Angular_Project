package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.model.Doctor;
import com.amaze_care.model.OPDPrescription;
import com.amaze_care.model.PatientOPD;
import com.amaze_care.service.OPDPrescriptionService;



@RestController
public class OPDPrescriptionController {
	
	@Autowired
	private OPDPrescriptionService opdPrescriptionService;
	
	@PostMapping("/add-prescription/{opdPatientId}/{doctorId}")
	public ResponseEntity<?> addPrescription(
	        @PathVariable int opdPatientId,
	        @PathVariable int doctorId,
	        @RequestBody OPDPrescription prescription,
	        MessageDto messageDto) {
	    try {
	        

	        OPDPrescription savedPrescription = opdPrescriptionService.addPrescription(opdPatientId,doctorId,prescription);
	        return ResponseEntity.ok(savedPrescription);
	    } catch (Exception e) {
	        messageDto.setMsg(e.getMessage());
	        return ResponseEntity.badRequest().body(messageDto);
	    }
	}


}
