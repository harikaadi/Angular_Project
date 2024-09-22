package com.amaze_care.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaze_care.model.Admission;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {

}
