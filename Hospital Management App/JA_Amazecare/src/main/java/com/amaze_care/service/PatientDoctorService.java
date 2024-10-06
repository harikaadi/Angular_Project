package com.amaze_care.service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaze_care.exception.AppointmentUnavailableException;
import com.amaze_care.model.Doctor;
import com.amaze_care.model.DoctorSchedule;
import com.amaze_care.model.PatientDoctor;
import com.amaze_care.model.PatientOPD;
import com.amaze_care.repo.DoctorRepository;
import com.amaze_care.repo.DoctorScheduleRepository;
import com.amaze_care.repo.PatientDoctorRepository;
import com.amaze_care.repo.PatientOPDRepository;

@Service
public class PatientDoctorService {
	@Autowired
	private PatientOPDRepository patientOPDRepository;
	//private PatientOPDRepository patientOPDRepository;
	
	
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientDoctorRepository patientDoctorRepository;
	
	@Autowired
	private DoctorScheduleRepository doctorScheduleRepository;
	
	

public PatientDoctor bookAppointment(Principal principal, int doctorId, PatientDoctor patientDoctor) throws AppointmentUnavailableException {
	
	//PatientOPD patientOPD =  patientOPDRepository.findById(patientId).get(); 
	Optional<PatientOPD> patientOPD = patientOPDRepository.getByUsername(principal.getName());
	Doctor doctor =  doctorRepository.findById(doctorId).get(); 
	
	patientDoctor.setDoctor(doctor);
	patientDoctor.setPatientOpd(patientOPD.get());
	
	
	List<DoctorSchedule> doctorSchedule =  doctorScheduleRepository.getScheduleByDoctorId(doctorId);
	
	
	List<LocalDate> listDates =  doctorSchedule.stream().map(e->e.getDate()).toList();
	
	if(! (listDates.contains(patientDoctor.getDateOfAppointment()))) {
		throw new AppointmentUnavailableException("Appointment not available at this date"); 
	}
	
	List<String> listSlots = new ArrayList<>();
	for( DoctorSchedule ds :doctorSchedule ) {
		String fromTime = ds.getFromTime().toString().split(":00.000000")[0];
		String toTime = ds.getToTime().toString().split(":00.000000")[0];
		String slot = fromTime + "-" + toTime;
		listSlots.add(slot);
	}
	System.out.println("----list of all slots------");
	listSlots.stream().forEach(System.out :: println);
	
	System.out.println("Given slot: " + patientDoctor.getSlot());
	
	
	if( !(listSlots.contains(patientDoctor.getSlot()))) {
		throw new AppointmentUnavailableException("Appointment not available at this slot"); 
	}
	
	LocalTime fromTime = LocalTime.parse(patientDoctor.getSlot().split("-")[0]);    
	LocalTime toTime = LocalTime.parse(patientDoctor.getSlot().split("-")[1]);
	
	DoctorSchedule ds = patientDoctorRepository.getNumberOfAppointmentCount(patientDoctor.getDateOfAppointment(), fromTime, toTime);
	if(ds.getNumberOfAppt() < 1) {
		throw new AppointmentUnavailableException("Appointments are full at this slot"); 
	}
	
	
	patientDoctor.setDateOfBooking(LocalDate.now());
	ds.setNumberOfAppt(ds.getNumberOfAppt() - 1);; 
	doctorScheduleRepository.save(ds);
	return patientDoctorRepository.save(patientDoctor);
}
}