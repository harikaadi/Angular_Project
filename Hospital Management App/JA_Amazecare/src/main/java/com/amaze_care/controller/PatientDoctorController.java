package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class PatientDoctorController {
	
	@Autowired
	private PatientOPDService patientOPDService;
	
	@Autowired
	private PatientDoctorService patientDoctorService;
	
	@PostMapping("/book-appointment/{patientId}/{doctorId}")
	public ResponseEntity<?> bookAppointment(@PathVariable int patientId, 
	                                         @PathVariable int doctorId, 
	                                         @RequestBody PatientDoctor patientDoctor, 
	                                         MessageDto messageDto) {
	    try {
	        // Call the service to book the appointment
	        PatientDoctor bookedAppointment = patientDoctorService.bookAppointment(patientId, doctorId, patientDoctor);
	        return ResponseEntity.ok(bookedAppointment);  // Return the booked PatientDoctor object
	    } catch (AppointmentUnavailableException e) {
	        // Set the error message in MessageDto
	        messageDto.setMsg(e.getMessage());
	        return ResponseEntity.badRequest().body(messageDto);  // Return the error message with bad request status
	    }
	}


}
