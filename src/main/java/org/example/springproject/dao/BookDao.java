package org.example.springproject.dao;

import org.example.springproject.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> findAll();

    List<Book> findAllPageable(int limit, int offset);

    Optional<Book> findBookById(long id);

    List<Book> findBooksByAuthorId(long id);

    void save(Book book, long authorId);

    void updateBookTitle(Book book, long id);

    void delete(long id);
}
