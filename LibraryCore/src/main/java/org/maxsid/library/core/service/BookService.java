package org.maxsid.library.core.service;

import org.maxsid.library.core.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    Book findBookById(long id);

    void save(Book book, long authorId);

    void updateBook(Book book, long id);

    void delete(long id);
}
