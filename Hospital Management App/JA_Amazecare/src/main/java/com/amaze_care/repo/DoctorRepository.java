package com.amaze_care.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaze_care.enums.Specialization;
import com.amaze_care.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	
	Optional<List<Doctor>> findBySpecialization(Specialization specialization);
	Optional<List<Doctor>> findByAvailable(Boolean available);

}
