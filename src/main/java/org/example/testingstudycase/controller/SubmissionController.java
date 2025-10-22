package org.example.testingstudycase.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.testingstudycase.model.Submission;
import org.example.testingstudycase.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {

  private final SubmissionService submissionService;

  @GetMapping
  public List<Submission> getAllSubmissions() {
    return submissionService.getAllSubmissions();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id) {
    return submissionService.getSubmissionById(id);
  }

  @PostMapping
  public ResponseEntity<Submission> createSubmission(@RequestBody Submission submission) {
    return submissionService.createSubmission(submission);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Submission> updateSubmission(@PathVariable Long id,
      @RequestBody Submission submissionDetails) {
    return submissionService.updateSubmission(id, submissionDetails);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
    return submissionService.deleteSubmission(id);
  }
}
