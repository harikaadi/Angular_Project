package com.amaze_care.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amaze_care.model.DoctorSchedule;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule,Integer> {
	
	@Query("select ds from DoctorSchedule ds where ds.doctor.id =?1")
	List<DoctorSchedule> getScheduleByDoctorId(int doctorId);

}
