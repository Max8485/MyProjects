package org.example.springproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springproject.entity.Author;
import org.example.springproject.entity.Book;
import org.example.springproject.exceptions.AuthorNotFoundException;
import org.example.springproject.exceptions.BookNotFoundException;
import org.example.springproject.repository.AuthorRepository;
import org.example.springproject.repository.BookRepository;
import org.example.springproject.service.BookService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Book findBookById(long id) { // работает!
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public void save(Book book, long authorId) { //работает!
        if (authorRepository.existsById(authorId)) {
            book.setAuthor(Author.builder()
                    .id(authorId)
                    .build());
            bookRepository.save(book);
        } else {
            throw new AuthorNotFoundException();
        }
    }

    @Override
    public void updateBook(Book book, long id, long authorId) { //работает!
        if (bookRepository.existsById(id)) {
            book.setId(id);
            book.setAuthor(Author.builder()
                    .id(authorId)
                    .build());
            bookRepository.save(book);
        } else {
            throw new BookNotFoundException();
        }
    }

    @Override
    public void delete(long id) { //работает!
        bookRepository.deleteById(id);
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
