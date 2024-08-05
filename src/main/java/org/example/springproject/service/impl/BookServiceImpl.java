package org.example.springproject.service.impl;

import org.example.springproject.dao.AuthorDao;
import org.example.springproject.dao.BookDao;
import org.example.springproject.exceptions.AuthorNotFoundException;
import org.example.springproject.exceptions.BookNotFoundException;
import org.example.springproject.models.Book;
import org.example.springproject.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findAllPageable(int limit, int offset) { //сделать пагинацию! работает!
        return bookDao.findAllPageable(limit, offset);
    }

    @Override
    public Book findBookById(long id) {
        return bookDao.findBookById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<Book> findBooksByAuthorId(long id) {
        return bookDao.findBooksByAuthorId(id);
    }

    @Override
    public void save(Book book, long authorId) { //РАБОТАЕТ!
        if (authorDao.existsById(authorId)) {
            bookDao.save(book, authorId);
        } else {
            throw new AuthorNotFoundException();
        }
    }

    @Override
    public void updateBookTitle(Book book, long id) {
        bookDao.updateBookTitle(book, id);
    }

    @Override
    public void delete(long id) {
        bookDao.delete(id);
    }
}
