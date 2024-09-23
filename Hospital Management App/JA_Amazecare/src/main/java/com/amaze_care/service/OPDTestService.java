package com.amaze_care.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.Doctor;
import com.amaze_care.model.PatientOPD;
import com.amaze_care.model.OPDTest;
import com.amaze_care.model.Test;
import com.amaze_care.repo.DoctorRepository;
import com.amaze_care.repo.PatientOPDRepository;
import com.amaze_care.repo.OPDTestRepository;
import com.amaze_care.repo.TestRepository;

@Service
public class OPDTestService {

    @Autowired
    private OPDTestRepository opdTestRepo;
    
    @Autowired
    private PatientOPDRepository patientOPDRepo;
    
    @Autowired
    private DoctorRepository doctorRepo;
    
    @Autowired
    private TestRepository testRepo;

    public OPDTest assignTestToPatientOPD(int patientOPDId, int doctorId, int testId) throws InvalidIdException {
        
        // Fetching patient by id
        Optional<PatientOPD> optionalPatientOPD = patientOPDRepo.findById(patientOPDId);
        if (optionalPatientOPD.isEmpty()) {
            throw new InvalidIdException("Invalid patient OPD id");
        }
        PatientOPD patientOPD = optionalPatientOPD.get();
        
        // Fetching doctor by id
        Optional<Doctor> optionalDoctor = doctorRepo.findById(doctorId);
        if (optionalDoctor.isEmpty()) {
            throw new InvalidIdException("Invalid doctor id");
        }
        Doctor doctor = optionalDoctor.get();
        
        // Fetching test by id
        Optional<Test> optionalTest = testRepo.findById(testId);
        if (optionalTest.isEmpty()) {
            throw new InvalidIdException("Invalid test id");
        }
        Test test = optionalTest.get();
        
        // Creating and saving OPDTest
        OPDTest opdTest = new OPDTest();
        opdTest.setPatientOPD(patientOPD);
        opdTest.setDoctor(doctor);
        opdTest.setTest(test);
        opdTest.setDate(LocalDate.now());
        
        return opdTestRepo.save(opdTest);
    }
}

