package org.maxsid.library.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.core.entity.Author;
import org.maxsid.library.core.entity.Book;
import org.maxsid.library.core.exceptions.AuthorNotFoundException;
import org.maxsid.library.core.exceptions.BookNotFoundException;
import org.maxsid.library.core.repository.AuthorRepository;
import org.maxsid.library.core.repository.BookRepository;
import org.maxsid.library.core.service.BookService;
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
    public void updateBook(Book book, long id) { //работает!
        bookRepository.findById(id).ifPresentOrElse(
                b -> {
                    book.setAuthor(b.getAuthor());
                    book.setId(id);
                    bookRepository.save(book);
                },
                () -> {throw new BookNotFoundException();}
        );
    }

    @Override
    public void delete(long id) { //работает!
        bookRepository.deleteById(id);
    }
}
