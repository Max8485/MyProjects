package org.example.springproject.service;

import org.example.springproject.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> findAll();

    List<Book> findAllPageable(int pageSize, int pageNumber);

    Book findBookById(long id);

    List<Book> findBooksByAuthorId(long id);

    void save(Book book, long authorId);

    void updateBookTitle(Book book, long id);

    void delete(long id);
}
