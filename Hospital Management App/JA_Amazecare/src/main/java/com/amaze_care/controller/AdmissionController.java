package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.enums.CommonHealthIssues;
import com.amaze_care.enums.RoomType;
import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.exception.NoRoomsAvailableException;
import com.amaze_care.model.Admission;
import com.amaze_care.service.AdmissionService;

@RestController
public class AdmissionController {
	
	@Autowired
	private AdmissionService admissionservice;
	
	
	@PostMapping("addadmission/{patientid}/{issueName}/{roomtype}")
	public ResponseEntity<?> addAdmission(@PathVariable int patientid,@PathVariable CommonHealthIssues issueName, @PathVariable RoomType roomtype,@RequestBody Admission admission,MessageDto messagedto) {
		//System.out.println("inside add_admission controller");
		try {
			admission = admissionservice.addAdmission(patientid,issueName,roomtype,admission);
			return ResponseEntity.ok(admission); 
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage()); 
			//e.printStackTrace();
		} catch (NoRoomsAvailableException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage()); 
			//e.printStackTrace();
		}	
	}

}
