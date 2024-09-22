package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.DoctorSchedule;
import com.amaze_care.service.DoctorScheduleService;


@RestController
public class DoctorScheduleController {
	
	@Autowired
	private DoctorScheduleService doctorScheduleService;
	
	@PostMapping("/doctor/schedule/add/{doctorId}")
	public ResponseEntity<?> addDoctorSchedule(@PathVariable int doctorId, @RequestBody DoctorSchedule doctorSchedule,MessageDto messageDto) {
	    try {
	        // Call the service layer to add the doctor's schedule
	        DoctorSchedule ds = doctorScheduleService.addDoctorSchedule(doctorId, doctorSchedule);
	        return ResponseEntity.ok(ds);  // Return the saved schedule
	    } catch (InvalidIdException e) {
	        // Set the error message in the MessageDto
	        messageDto.setMsg(e.getMessage());
	        return ResponseEntity.badRequest().body(messageDto);  // Return the error message with a bad request status
	    }
	}


}
