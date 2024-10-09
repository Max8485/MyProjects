package org.example.springproject.service.impl;

import org.example.springproject.dataprovider.TestAuthorAndBook;
import org.example.springproject.entity.Author;
import org.example.springproject.entity.Book;
import org.example.springproject.exceptions.AuthorNotFoundException;
import org.example.springproject.repository.AuthorRepository;
import org.example.springproject.repository.BookRepository;
import org.example.springproject.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.IntStream;

//import static org.example.springproject.TestDataProvider.buildAuthor;
import static org.example.springproject.dataprovider.TestAuthorAndBook.buildAuthor;

@SpringBootTest
@ActiveProfiles("test")
class AuthorServiceImplTest {
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    AuthorServiceImplTest(AuthorService authorService, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    void deleteAll() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void findAll() { //работает!
        List<Author> authorList = buildAuthorWithBooks(2, 2);
        authorRepository.saveAll(authorList);

        List<Book> bookList = authorList.stream().flatMap(author -> author.getBooks().stream()).toList();
        bookRepository.saveAll(bookList);

        Page<Author> foundAuthors = authorService.findAll(Pageable.ofSize(authorList.size()));
        Assertions.assertEquals(authorList.size(), foundAuthors.getSize());

        foundAuthors.getContent().forEach(author -> Assertions.assertNull(author.getBooks()));

    }

    private List<Author> buildAuthorWithBooks(int countAuthors, int countBooks) {
        return IntStream.range(0, countAuthors)
                .mapToObj(TestAuthorAndBook::buildAuthor)
                .peek(author -> author.setBooks(
                                IntStream.range(0, countBooks).mapToObj(TestAuthorAndBook::buildBook)
                                        .peek(book -> book.setAuthor(author))
                                        .toList()
                        )
                ).toList();
    }

    @Test
    void findAllWithBooks() { //работает!
        List<Author> authorList = buildAuthorWithBooks(2, 2);
        authorRepository.saveAll(authorList);

        List<Book> bookList = authorList.stream().flatMap(author -> author.getBooks().stream()).toList();
        bookRepository.saveAll(bookList);

        Page<Author> foundAuthors = authorService.findAllWithBooks(Pageable.ofSize(authorList.size()));
        Assertions.assertEquals(authorList.size(), foundAuthors.getSize());

        foundAuthors.getContent().forEach(author -> Assertions.assertNotNull(author.getBooks()));
    }

    @Test
    void findAuthorById() { //работает!
        List<Author> authorList = buildAuthorWithBooks(1, 1);
        authorRepository.saveAll(authorList);

        List<Book> bookList = authorList.stream().flatMap(author -> author.getBooks().stream()).toList();
        bookRepository.saveAll(bookList);

        Author foundAuthorById = authorService.findAuthorById(authorList.get(0).getId());

        Assertions.assertEquals(authorList.get(0).getId(), foundAuthorById.getId());
    }

    @Test
    void findAuthorByIdShouldThrows() {
        Assertions.assertThrows(AuthorNotFoundException.class, () -> authorService.findAuthorById(Long.MAX_VALUE));
    }


    @Test
    void save() { //работает!
        Author author = buildAuthor(1);
        authorService.save(author);

        Author foundAuthorById = authorService.findAuthorById(author.getId());

        Assertions.assertEquals(author.getFirstName(), foundAuthorById.getFirstName());
        Assertions.assertEquals(author.getLastName(), foundAuthorById.getLastName());
        Assertions.assertEquals(author.getMiddleName(), foundAuthorById.getMiddleName());
    }

    @Test
    void updateAuthor() {
        String newAuthorName = "UpdatedName";
        Author author = buildAuthor(1);
        authorRepository.save(author);

        author.setFirstName(newAuthorName);
        authorService.updateAuthor(author, author.getId());
        Author foundAuthorById = authorService.findAuthorById(author.getId());

        Assertions.assertEquals(newAuthorName, foundAuthorById.getFirstName());
        Assertions.assertEquals(author.getMiddleName(), foundAuthorById.getMiddleName());
        Assertions.assertEquals(author.getLastName(), foundAuthorById.getLastName());
    }

    @Test
    void delete() { //работает!
        List<Author> authorList = buildAuthorWithBooks(1, 0);
        authorRepository.saveAll(authorList);

        Long authorId = authorList.get(0).getId();

        authorService.delete(authorId);

        Assertions.assertFalse(authorRepository.existsById(authorId));
    }
}