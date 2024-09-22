package com.amaze_care.repo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amaze_care.model.DoctorSchedule;
import com.amaze_care.model.PatientDoctor;

public interface PatientDoctorRepository extends JpaRepository<PatientDoctor,Integer>{
	
	@Query("select ds from DoctorSchedule ds where ds.date=?1 AND ds.fromTime = ?2 AND ds.toTime=?3")
	DoctorSchedule getNumberOfAppointmentCount(LocalDate dateOfAppointment, LocalTime fromTime, LocalTime toTime);



}
