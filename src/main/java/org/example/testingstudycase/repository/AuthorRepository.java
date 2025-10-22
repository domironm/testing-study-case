package org.example.testingstudycase.repository;

import org.example.testingstudycase.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

