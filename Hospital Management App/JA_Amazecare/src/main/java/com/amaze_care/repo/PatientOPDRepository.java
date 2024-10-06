package com.amaze_care.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amaze_care.model.PatientOPD;

@Repository
public interface PatientOPDRepository extends JpaRepository<PatientOPD,Integer>{

	
	@Query("select op from PatientOPD op where op.user.username=?1")
	Optional<PatientOPD> getByUsername(String name);
}
