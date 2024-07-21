package org.example.springproject.service.impl;

import org.example.springproject.dao.AuthorDao;
import org.example.springproject.dao.BookDao;
import org.example.springproject.exceptions.AuthorNotFoundException;
import org.example.springproject.models.Author;
import org.example.springproject.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public AuthorServiceImpl(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    public List<Author> findAllWithBooks() { // работает!
        return authorDao.findAll().stream().peek(author -> author.setBooks(bookDao.findBooksByAuthorId(author.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public Author findAuthorById(long id) {
        Author author = authorDao.findAuthorById(id).orElseThrow(AuthorNotFoundException::new);
        author.setBooks(bookDao.findBooksByAuthorId(author.getId()));
        return author;
    }

    @Override
    public void save(Author author) {
        authorDao.save(author);
    }

    @Override
    public void updateAuthorName(Author author, long id) {
        authorDao.updateAuthorName(author, id);
    }

    @Override
    public void delete(long id) {
        authorDao.delete(id);
    }
}
