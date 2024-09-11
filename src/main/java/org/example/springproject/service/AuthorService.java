package org.example.springproject.service;

import org.example.springproject.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    Page<Author> findAll(Pageable pageable);

    Page<Author> findAllWithBooks(Pageable pageable);

    Author findAuthorById(long id);

    Author save(Author author);

    void updateAuthor(Author author, long id);

    void delete(long id);

}
