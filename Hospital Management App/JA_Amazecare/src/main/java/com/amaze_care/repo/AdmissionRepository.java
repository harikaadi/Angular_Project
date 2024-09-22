package com.amaze_care.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amaze_care.model.Admission;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {
	
	// Corrected Query to count the number of patients assigned to a doctor
    @Query("SELECT COUNT(ad) FROM Admission ad WHERE ad.doctor.Id = ?1")
    int countPatientsByDoctorId(int doctorId);

}
