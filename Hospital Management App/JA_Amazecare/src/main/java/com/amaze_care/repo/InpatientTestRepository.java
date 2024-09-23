package com.amaze_care.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amaze_care.model.InPatientTest;
@Repository
public interface InpatientTestRepository extends JpaRepository<InPatientTest, Integer> {
	
	@Query("select i from InPatientTest i where i.inpatient.id=?1")
	List<InPatientTest> findinpatinettest(int patinetid);
	

}
