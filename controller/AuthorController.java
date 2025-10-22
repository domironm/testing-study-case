package org.example.testingstudycase.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.testingstudycase.model.Author;
import org.example.testingstudycase.service.AuthorService;
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
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorService authorService;

  @GetMapping
  public List<Author> getAllAuthors() {
    return authorService.getAllAuthors();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
    return authorService.getAuthorById(id);
  }

  @PostMapping
  public Author createAuthor(@RequestBody Author author) {
    return authorService.createAuthor(author);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Author> updateAuthor(@PathVariable Long id,
      @RequestBody Author authorDetails) {
    return authorService.updateAuthor(id, authorDetails);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
    return authorService.deleteAuthor(id);
  }
}

