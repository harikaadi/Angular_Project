package com.amaze_care.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.OPDTest;
import com.amaze_care.model.OPDTestResult;
import com.amaze_care.repo.OPDTestRepository;
import com.amaze_care.repo.OPDTestResultRepository;

@Service
public class OPDTestResultService {

    @Autowired
    private OPDTestResultRepository opdTestResultRepository;

    @Autowired
    private OPDTestRepository opdTestRepository;

    public OPDTestResult addTestResult(MultipartFile file, int opdTestId) throws IOException, InvalidIdException {
        
        // Fetching the OPD test by ID
        Optional<OPDTest> optionalOpdTest = opdTestRepository.findById(opdTestId);
        if (optionalOpdTest.isEmpty()) {
            throw new InvalidIdException("Invalid OPD test ID");
        }
        OPDTest opdTest = optionalOpdTest.get();
        
        // Saving the OPD test into OPD test result
        OPDTestResult opdTestResult = new OPDTestResult();
        
        opdTestResult.setOpdTest(opdTest);
        
        String filename = file.getOriginalFilename();
        File destinationFile = new File("C:/Users/Aarthi/Documents/GitHub/Angular_Project/Hospital Management App/JA_Amazecare/src/main/resources/static/reports/opd/" + filename);
        
        // Creating a new file stream
        try (FileOutputStream fos = new FileOutputStream(destinationFile)) {
            InputStream is = file.getInputStream();
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            fos.write(bytes);
        }
        
        opdTestResult.setFileName(filename);
        opdTestResult.setFilePath(destinationFile.getAbsolutePath());
        opdTestResult.setUploadedAt(LocalDate.now());
        
        return opdTestResultRepository.save(opdTestResult);
    }
}

