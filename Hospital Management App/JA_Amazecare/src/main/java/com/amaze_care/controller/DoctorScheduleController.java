package com.amaze_care.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.Doctor;
import com.amaze_care.model.DoctorSchedule;
import com.amaze_care.repo.DoctorRepository;
import com.amaze_care.service.DoctorScheduleService;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class DoctorScheduleController {
	
	@Autowired
	private DoctorScheduleService doctorScheduleService;
	
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@PostMapping("/doctor/schedule/add")
	public ResponseEntity<?> addDoctorSchedule(Principal principal, @RequestBody DoctorSchedule doctorSchedule) {
		try {
			
			DoctorSchedule ds=doctorScheduleService.addDoctorSchedule(principal.getName(),doctorSchedule);
			return ResponseEntity.ok(ds);

		} catch (InvalidIdException e) {
			 return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}


}
