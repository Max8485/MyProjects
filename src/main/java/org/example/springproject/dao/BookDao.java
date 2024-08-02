package org.example.springproject.dao;

import org.example.springproject.models.Author;
import org.example.springproject.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> findAll();

    Optional<Book> findBookById(long id);

    List<Book> findBooksByAuthorId(long id);

    void save(Book book);

    void updateBookTitle(Book book, long id);

    void delete(long id);

    // existsById() {

}
