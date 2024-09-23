package com.amaze_care.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amaze_care.model.OPDTest;

@Repository
public interface OPDTestRepository extends JpaRepository<OPDTest, Integer> {
    
    @Query("select o from OPDTest o where o.patientOPD.id = ?1")
    List<OPDTest> findOPDTestByPatientId(int patientOPDId);
}

