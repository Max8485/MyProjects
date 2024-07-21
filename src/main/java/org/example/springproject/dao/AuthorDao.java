package org.example.springproject.dao;

import org.example.springproject.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    List<Author> findAll();

    List<Author> findAllWithBooks();

    Optional<Author> findAuthorById(long id);

    void save(Author author);

    void updateAuthorName(Author author, long id);

    void delete(long id);
}
