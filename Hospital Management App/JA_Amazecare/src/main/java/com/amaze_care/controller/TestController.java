package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.model.Test;
import com.amaze_care.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService testservice;
	
	@PostMapping("/test/add")
	public ResponseEntity<?> addTest(@RequestBody Test testinp,MessageDto messageDto) {
		try {
		Test savedtestinp= testservice.addTest(testinp);
		 return ResponseEntity.ok(savedtestinp);
    } catch (Exception e) {
        messageDto.setMsg(e.getMessage());
        return ResponseEntity.badRequest().body(messageDto);
    }
		
	}

}
