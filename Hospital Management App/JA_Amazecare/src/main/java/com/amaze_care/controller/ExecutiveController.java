package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.model.Executive;
import com.amaze_care.service.ExecutiveService;


@RestController
public class ExecutiveController {
	
	@Autowired
	private ExecutiveService executiveservice;
	
	@PostMapping("/executive/add")
	public ResponseEntity<?> addTest(@RequestBody Executive executive,MessageDto messageDto) {
		try {
			Executive savedexecutive= executiveservice.addexecutive(executive);
		 return ResponseEntity.ok(savedexecutive);
    } catch (Exception e) {
        messageDto.setMsg(e.getMessage());
        return ResponseEntity.badRequest().body(messageDto);
    }
		
	}

}
