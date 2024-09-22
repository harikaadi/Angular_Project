package com.amaze_care.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.Doctor;
import com.amaze_care.model.DoctorSchedule;
import com.amaze_care.repo.DoctorRepository;
import com.amaze_care.repo.DoctorScheduleRepository;


@Service
public class DoctorScheduleService {
	private Logger logger = LoggerFactory.getLogger(DoctorService.class);
	
	
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private DoctorScheduleRepository doctorScheduleRepository;

	public DoctorSchedule addDoctorSchedule(int doctorId, DoctorSchedule doctorSchedule) throws InvalidIdException {

	    logger.info("Attempting to add schedule for doctor ID: " + doctorId);

	    Optional<Doctor> optional =  doctorRepository.findById(doctorId);
	    if (optional.isEmpty()) {
	        logger.error("Invalid Doctor ID: " + doctorId);
	        throw new InvalidIdException("Doctor ID Invalid");
	    }

	    Doctor doctor = optional.get();
	    doctorSchedule.setDoctor(doctor);
	    
	    logger.info("Doctor found: " + doctor.getName() + " (ID: " + doctorId + ")");
	    logger.info("Adding schedule: " + doctorSchedule);

	    DoctorSchedule savedSchedule = doctorScheduleRepository.save(doctorSchedule);
	    
	    logger.info("Doctor schedule added successfully for doctor ID: " + doctorId);
	    
	    return savedSchedule;
	}


}
