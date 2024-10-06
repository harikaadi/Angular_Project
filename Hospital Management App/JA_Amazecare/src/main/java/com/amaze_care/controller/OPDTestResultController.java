package com.amaze_care.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amaze_care.dto.MessageDto;
import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.OPDTestResult;
import com.amaze_care.service.OPDTestResultService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class OPDTestResultController {
    
    @Autowired
    private OPDTestResultService opdTestResultService;
    
    @PostMapping("/opdtestresult/add/{opdTestId}")
    public ResponseEntity<?> addTestResult(@RequestBody MultipartFile file, @PathVariable int opdTestId, MessageDto messageDto) throws IOException {
        System.out.println("Inside addTestResult method");
        
        try {
            OPDTestResult uploadedTest = opdTestResultService.addTestResult(file, opdTestId);
            return ResponseEntity.ok(uploadedTest);
        } catch (IOException e) {
            messageDto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(messageDto);
        } catch (InvalidIdException e) {
            messageDto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(messageDto);
        }
    }
}

