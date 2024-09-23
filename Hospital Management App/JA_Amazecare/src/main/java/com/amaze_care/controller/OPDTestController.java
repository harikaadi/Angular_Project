package com.amaze_care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.OPDTest;
import com.amaze_care.service.OPDTestService;
import com.amaze_care.service.PatientOPDService;

@RestController
public class OPDTestController {
    
    @Autowired
    private PatientOPDService patientOPDService;
    
    @Autowired
    private OPDTestService opdTestService;

    @PostMapping("/opd-test/add/{patientOPDId}/{doctorId}/{testId}")
    public ResponseEntity<?> assignTestToPatientOPD(@PathVariable int patientOPDId, 
                                                    @PathVariable int doctorId, 
                                                    @PathVariable int testId, 
                                                    MessageDto messageDto) {
    	try {
            OPDTest opdTest = opdTestService.assignTestToPatientOPD(patientOPDId, doctorId, testId);
            return ResponseEntity.ok(opdTest);
        } catch (InvalidIdException e) {
            messageDto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(messageDto);
        }
    }
}
