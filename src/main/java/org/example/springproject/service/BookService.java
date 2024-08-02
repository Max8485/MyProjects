package org.example.springproject.service;

import org.example.springproject.models.Author;
import org.example.springproject.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> findAll();

    Book findBookById(long id);

    List<Book> findBooksByAuthorId(long id);

    void save(Book book);

    void updateBookTitle(Book book, long id);

    void delete(long id);
}
