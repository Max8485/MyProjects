package org.maxsid.library.core.service;

import org.maxsid.library.core.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    Page<Author> findAll(Pageable pageable);

    Page<Author> findAllWithBooks(Pageable pageable);

    Author findAuthorById(long id);

    Author save(Author author);

    void updateAuthor(Author author, long id);

    void delete(long id);

}
