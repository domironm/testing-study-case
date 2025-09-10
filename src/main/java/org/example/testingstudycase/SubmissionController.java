package org.example.testingstudycase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionRepository submissionRepository;

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id) {
        Optional<Submission> submission = submissionRepository.findById(id);
        return submission.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        return submissionRepository.save(submission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> updateSubmission(@PathVariable Long id, @RequestBody Submission submissionDetails) {
        Optional<Submission> optionalSubmission = submissionRepository.findById(id);
        if (optionalSubmission.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Submission submission = optionalSubmission.get();
        submission.setTitle(submissionDetails.getTitle());
        submission.setAuthor(submissionDetails.getAuthor());
        submission.setAbstractText(submissionDetails.getAbstractText());
        Submission updatedSubmission = submissionRepository.save(submission);
        return ResponseEntity.ok(updatedSubmission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        if (!submissionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        submissionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

