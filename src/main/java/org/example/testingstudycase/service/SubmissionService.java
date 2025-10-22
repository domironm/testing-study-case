package org.example.testingstudycase.service;

import lombok.RequiredArgsConstructor;
import org.example.testingstudycase.model.Author;
import org.example.testingstudycase.model.Submission;
import org.example.testingstudycase.repository.AuthorRepository;
import org.example.testingstudycase.repository.SubmissionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final AuthorRepository authorRepository;

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public ResponseEntity<Submission> getSubmissionById(Long id) {
        Optional<Submission> submission = submissionRepository.findById(id);
        return submission.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Submission> createSubmission(Submission submission) {
        if (submission.getAuthors() == null || submission.getAuthors().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Long> authorIds = submission.getAuthors().stream()
                .map(Author::getId)
                .collect(Collectors.toList());
        if (authorIds.contains(null)) {
            return ResponseEntity.badRequest().build();
        }
        List<Author> authors = authorRepository.findAllById(authorIds);
        if (authors.size() != authorIds.size()) {
            return ResponseEntity.badRequest().build();
        }
        submission.setAuthors(authors);
        Submission saved = submissionRepository.save(submission);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<Submission> updateSubmission(Long id, Submission submissionDetails) {
        Optional<Submission> optionalSubmission = submissionRepository.findById(id);
        if (optionalSubmission.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Submission submission = optionalSubmission.get();
        submission.setTitle(submissionDetails.getTitle());
        if (submissionDetails.getAuthors() != null && !submissionDetails.getAuthors().isEmpty()) {
            List<Long> authorIds = submissionDetails.getAuthors().stream()
                    .map(Author::getId)
                    .collect(Collectors.toList());
            if (authorIds.contains(null)) {
                return ResponseEntity.badRequest().build();
            }
            List<Author> authors = authorRepository.findAllById(authorIds);
            if (authors.size() != authorIds.size()) {
                return ResponseEntity.badRequest().build();
            }
            submission.setAuthors(authors);
        }
        Submission updated = submissionRepository.save(submission);
        return ResponseEntity.ok(updated);
    }

    public ResponseEntity<Void> deleteSubmission(Long id) {
        if (!submissionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        submissionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
