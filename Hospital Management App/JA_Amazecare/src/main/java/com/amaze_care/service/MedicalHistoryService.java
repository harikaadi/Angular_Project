package com.amaze_care.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.InPatient;
import com.amaze_care.model.MedicalHistory;
import com.amaze_care.repo.InPatientRepository;
import com.amaze_care.repo.MedicalHistoryRepository;


@Service
public class MedicalHistoryService {
	
	@Autowired
	private MedicalHistoryRepository medicalHistoryRepository;
	@Autowired
	private InPatientRepository inpatientRepository;

	public MedicalHistory addPatientHistory(int pid, MedicalHistory medicalHistory) throws InvalidIdException {
		//fetch PatinetOPD basis pid
		Optional<InPatient>optional=inpatientRepository.findById(pid);
		if(optional.isEmpty())
			throw new InvalidIdException("Patient ID Invalid"); 
		InPatient inpatient=optional.get();
		medicalHistory.setInPatient(inpatient);
		return medicalHistoryRepository.save(medicalHistory);
	}
}
