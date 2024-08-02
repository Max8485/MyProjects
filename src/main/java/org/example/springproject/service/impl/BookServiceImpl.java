package org.example.springproject.service.impl;

import org.example.springproject.dao.AuthorDao;
import org.example.springproject.dao.BookDao;
import org.example.springproject.exceptions.AuthorNotFoundException;
import org.example.springproject.exceptions.BookNotFoundException;
import org.example.springproject.models.Author;
import org.example.springproject.models.Book;
import org.example.springproject.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Book findBookById(long id) {
        return bookDao.findBookById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<Book> findBooksByAuthorId(long id) {
        return bookDao.findBooksByAuthorId(id);
    }

    @Override
    public void save(Book book) { //+ authorId
        //exsistsById(authorId)
        //if (true) {
        //
       //

        //{
        //Else { //exception не найден автор
        bookDao.save(book); //+ authorId
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
