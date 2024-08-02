package org.example.springproject.service;

import org.example.springproject.models.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    List<Author> findAllWithBooks();

    Author findAuthorById(long id);

    Author save(Author author);

    void updateAuthorName(Author author, long id);

    void delete(long id);
}
