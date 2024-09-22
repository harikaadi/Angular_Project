package com.amaze_care.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amaze_care.enums.CommonHealthIssues;
import com.amaze_care.model.HealthIssue;

@Repository
public interface HealthIssueRepository extends JpaRepository<HealthIssue, Integer> {
	
	Optional<HealthIssue> findByIssueName(CommonHealthIssues issueName);

}
