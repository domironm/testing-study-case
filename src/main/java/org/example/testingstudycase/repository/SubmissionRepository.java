package org.example.testingstudycase.repository;

import org.example.testingstudycase.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
