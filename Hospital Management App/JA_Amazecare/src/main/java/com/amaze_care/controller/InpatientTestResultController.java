package com.amaze_care.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.InpatientTestResult;
import com.amaze_care.service.InpatientTestResultService;

@RestController
public class InpatientTestResultController {
	
	@Autowired
	private InpatientTestResultService  inpatientTestResultService;
	
	@PostMapping("/testresult/add/{inpatienttestid}")
	public ResponseEntity<?> addTestResult(@RequestBody MultipartFile file,@PathVariable int inpatienttestid,MessageDto mesagedto) throws IOException {
		System.out.println("inside addtestresult method");
		
		try {
			InpatientTestResult uplodedtest= inpatientTestResultService.addTestResult(file,inpatienttestid);
			return ResponseEntity.ok(uplodedtest);
		} catch (IOException e) {
			mesagedto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(mesagedto);
			
		} catch (InvalidIdException e) {
			mesagedto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(mesagedto);
				
		}
	}
}
