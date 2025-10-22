package org.example.testingstudycase.service;

import lombok.RequiredArgsConstructor;
import org.example.testingstudycase.model.Author;
import org.example.testingstudycase.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public ResponseEntity<Author> getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public ResponseEntity<Author> updateAuthor(Long id, Author authorDetails) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Author author = optionalAuthor.get();
        author.setName(authorDetails.getName());
        author.setMail(authorDetails.getMail());
        Author updatedAuthor = authorRepository.save(author);
        return ResponseEntity.ok(updatedAuthor);
    }

    public ResponseEntity<Void> deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        authorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

