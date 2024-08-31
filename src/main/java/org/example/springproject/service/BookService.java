package org.example.springproject.service;

import org.example.springproject.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    Book findBookById(long id);

    void save(Book book, long authorId);

    void updateBook(Book book, long id, long authorId);

    void delete(long id);

    void deleteAll();
}
