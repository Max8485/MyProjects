package org.maxsid.library.core.service.impl;

import org.maxsid.library.core.dataprovider.TestAuthorAndBook;
import org.maxsid.library.core.entity.Author;
import org.maxsid.library.core.entity.Book;
import org.maxsid.library.core.repository.AuthorRepository;
import org.maxsid.library.core.repository.BookRepository;
import org.maxsid.library.core.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.IntStream;


@SpringBootTest
@ActiveProfiles("test")
class BookServiceImplTest {
    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    BookServiceImplTest(BookService bookService, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    void deleteAll() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    private List<Author> buildAuthorWithBooks(int countAuthors, int countBooks) {
        return IntStream.range(0, countAuthors)
                .mapToObj(TestAuthorAndBook::buildAuthor)
                .peek(author -> author.setBooks(
                        IntStream.range(0, countBooks)
                                .mapToObj(TestAuthorAndBook::buildBook)
                                .peek(book -> book.setAuthor(author))
                                .toList()
                )).toList();
    }

    @Test
    void findBookById() { //работает!
        List<Author> authorList = buildAuthorWithBooks(1, 1);
        authorRepository.saveAll(authorList);

        List<Book> bookList = authorList.stream().flatMap(author -> author.getBooks().stream()).toList();
        bookRepository.saveAll(bookList);

        Long bookId = bookList.get(0).getId();
        Book foundBookById = bookService.findBookById(bookId);

        Assertions.assertEquals(bookId, foundBookById.getId());
    }

    @Test
    void save() { //работает!
        Author author = TestAuthorAndBook.buildAuthor(1);
        authorRepository.save(author);
        Book book = TestAuthorAndBook.buildBook(1);
        book.setAuthor(author);
        bookRepository.save(book);

        Book foundedBook = bookService.findBookById(book.getId());

        Assertions.assertEquals(book.getTitle(), foundedBook.getTitle());
    }

    @Test
    void updateBook() { //работает!
        Author author = TestAuthorAndBook.buildAuthor(1);
        authorRepository.save(author);
        String newTitle = "UpdatedTitle";
        Book book = TestAuthorAndBook.buildBook(1);
        book.setAuthor(author);
        bookRepository.save(book);

        book.setTitle(newTitle);
        bookService.updateBook(book, book.getId());
        Book foundedBook = bookService.findBookById(book.getId());

        Assertions.assertEquals(newTitle, foundedBook.getTitle());
    }

    @Test
    void delete() { //работает!
        Author author = TestAuthorAndBook.buildAuthor(1);
        authorRepository.save(author);
        Book book = TestAuthorAndBook.buildBook(1);
        book.setAuthor(author);
        bookRepository.save(book);

        bookService.delete(book.getId());

        Assertions.assertFalse(bookRepository.existsById(book.getId()));
    }
}