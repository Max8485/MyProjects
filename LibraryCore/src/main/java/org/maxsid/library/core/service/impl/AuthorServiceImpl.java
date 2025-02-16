package org.maxsid.library.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.core.entity.Author;
import org.maxsid.library.core.exceptions.AuthorAlreadyExistsException;
import org.maxsid.library.core.exceptions.AuthorNotFoundException;
import org.maxsid.library.core.repository.AuthorRepository;
import org.maxsid.library.core.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Page<Author> findAllWithBooks(Pageable pageable) {
        return authorRepository.findAllWithBooks(pageable);
    }

    @Override
    public Author findAuthorById(long id) {
        return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public Author save(Author author) {
        boolean isExists = authorRepository.existsByUniqueIndex(author.getFirstName(), author.getLastName(), author.getMiddleName(), author.getDateOfBirth());
        if (isExists) {
            throw new AuthorAlreadyExistsException();
        }
        return authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Author author, long id) {
        //existsByID - true - обновляем!
        boolean existsById = authorRepository.existsById(id);
        if (existsById) {
            author.setId(id);
            authorRepository.save(author);
        } else {
            throw new AuthorNotFoundException();
        }
    }

    @Override
    public void delete(long id) {
        authorRepository.deleteById(id);
    }

}
