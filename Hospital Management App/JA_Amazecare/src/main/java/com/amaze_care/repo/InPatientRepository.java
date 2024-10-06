package com.amaze_care.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amaze_care.model.InPatient;

@Repository
public interface InPatientRepository  extends JpaRepository<InPatient, Integer>{

	
	@Query("select ip from InPatient ip where ip.user.username=?1")
	Optional<InPatient> getByUsername(String name);
}
