package com.amaze_care.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.enums.Day;
import com.amaze_care.enums.Specialization;
import com.amaze_care.exception.InvalidSpecializationException;
import com.amaze_care.exception.NoDoctorsAvailableException;
import com.amaze_care.model.Doctor;
import com.amaze_care.service.DoctorService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/doctor")
public class DoctorController {
	
	
	private Logger logger = LoggerFactory.getLogger(DoctorService.class);

	@Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor, MessageDto messageDto) {
        try {
            Doctor savedDoctor = doctorService.addDoctor(doctor);
            return ResponseEntity.ok(savedDoctor);
        } catch (Exception e) {
            messageDto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(messageDto);
        }
    }
    
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<?> getDoctorsBySpecialization(@PathVariable Specialization specialization, MessageDto messageDto) {
    	System.out.println("inside /doctor/specialization/Cardiology");
        try {
            List<Doctor> doctors = doctorService.getDoctorsBySpecialization(specialization);
            return ResponseEntity.ok(doctors);
        } catch (InvalidSpecializationException e) {
            messageDto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(messageDto);
        }
    }
    
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableDoctors(MessageDto messageDto) {
    	System.out.println("inside avalible");
        try {
            List<Doctor> doctors = doctorService.getAvailableDoctors();
            return ResponseEntity.ok(doctors);
        } catch (NoDoctorsAvailableException e) {
            messageDto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(messageDto);
        }
    }
    @GetMapping("/days")
	public List<Day> getAllDays(){
		return List.of(Day.values());
	}

}
