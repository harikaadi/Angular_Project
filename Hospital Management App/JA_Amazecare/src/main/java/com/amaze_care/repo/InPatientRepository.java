package com.amaze_care.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaze_care.model.InPatient;

@Repository
public interface InPatientRepository  extends JpaRepository<InPatient, Integer>{

}
