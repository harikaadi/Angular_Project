package com.amaze_care.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.exception.AppointmentUnavailableException;
import com.amaze_care.model.PatientDoctor;
import com.amaze_care.service.PatientDoctorService;
import com.amaze_care.service.PatientOPDService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class PatientDoctorController {
	
	
	
	@Autowired
	private PatientDoctorService patientDoctorService;
	
	@PostMapping("/book-appointment/{doctorId}")
	public ResponseEntity<?> bookAppointment(Principal principal, 
	                                         @PathVariable int doctorId, 
	                                         @RequestBody PatientDoctor patientDoctor, 
	                                         MessageDto messageDto) {
	    try {
	        
	        PatientDoctor bookedAppointment = patientDoctorService.bookAppointment(principal, doctorId, patientDoctor);
	        return ResponseEntity.ok(bookedAppointment); 
	    } catch (AppointmentUnavailableException e) {
	        
	        messageDto.setMsg(e.getMessage());
	        return ResponseEntity.badRequest().body(messageDto); 
	    }
	}


}
