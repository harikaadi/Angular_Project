package com.amaze_care.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaze_care.model.Executive;

@Repository
public interface ExecutiveRepository extends JpaRepository<Executive, Integer> {

}
