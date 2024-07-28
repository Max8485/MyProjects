package org.example.springproject.dao.impl;

import org.example.springproject.dao.AuthorDao;
import org.example.springproject.models.Author;
import org.example.springproject.models.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
class AuthorDaoImplTest {

    private final AuthorDao authorDao;

    @Autowired
    AuthorDaoImplTest(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @AfterEach
    void deleteAll() {
        authorDao.deleteAll();
    }

    @Test
    void findAllTest() { //работает!
        List<Book> bookList = new ArrayList<>(); //подготовка данных
        Author author1 = new Author(1L, "Александр", "Сергеевич", "Пушкин",
                LocalDate.of(1799, 6, 6), bookList);
        Author author2 = new Author(2L, "Джон", "Гриффит", "Чейни",
                LocalDate.of(1876, 12, 1), bookList);
        authorDao.save(author1);
        authorDao.save(author2);

        List<Author> authors = authorDao.findAll(); //тестируемое действие

        Assertions.assertEquals(2, authors.size()); //assertions
    }

    @Test
    void findAllWithBooksTest() {
        List<Book> bookList = new ArrayList<>();
        Author author1 = new Author(1L, "Александр", "Сергеевич", "Пушкин",
                LocalDate.of(1799, 6, 6), bookList);
        Author author2 = new Author(2L, "Джон", "Гриффит", "Чейни",
                LocalDate.of(1876, 12, 1), bookList);
        authorDao.save(author1);
        authorDao.save(author2);

        List<Author> allWithBooks = authorDao.findAllWithBooks();
        Assertions.assertEquals(2, allWithBooks.size());
    }

    @Test
    void findAuthorByIdTest() { //работает
        List<Book> bookList = new ArrayList<>();
        Author author = new Author(1L, "Александр", "Сергеевич", "Пушкин",
                LocalDate.of(1799, 6, 6), bookList);
        authorDao.save(author);

        Author foundAuthor = authorDao.findAuthorById(author.getId()).get();

        Assertions.assertEquals(author.getId(), foundAuthor.getId());
    }

    @Test
    void saveTest() { //работает!
        List<Book> bookList = new ArrayList<>();
        Author author = new Author(1L, "Александр", "Сергеевич", "Пушкин",
                LocalDate.of(1799, 6, 6), bookList);

        authorDao.save(author);

        Assertions.assertEquals(1, authorDao.findAll().size());
    }

    @Test
    void updateAuthorNameTest() {
        List<Book> bookList = new ArrayList<>();
        Author author = new Author(1L, "Александр", "Сергеевич", "Пушкин",
                LocalDate.of(1799, 6, 6), bookList);
        authorDao.save(author);

        Author newAuthorName = new Author(1L, "Джон", "Гриффит", "Чейни",
                LocalDate.of(1876, 12, 1), bookList);
        authorDao.updateAuthorName(newAuthorName, author.getId());

        Author foundAuthor = authorDao.findAuthorById(author.getId()).get();
        Assertions.assertEquals(newAuthorName, foundAuthor);

//        Assertions.assertEquals(newAuthorName.getFirstName(), foundAuthor.getFirstName());
//        Assertions.assertEquals(newAuthorName.getLastName(), foundAuthor.getLastName());
//        Assertions.assertEquals(newAuthorName.getMiddleName(), foundAuthor.getMiddleName());
//        Assertions.assertEquals(newAuthorName.getDateOfBirth(), foundAuthor.getDateOfBirth());
    }

    @Test
    void deleteTest() { //работате!
        List<Book> bookList = new ArrayList<>();
        Author author = new Author(1L, "Александр", "Сергеевич", "Пушкин",
                LocalDate.of(1799, 6, 6), bookList);
        authorDao.save(author);

        authorDao.delete(author.getId());

        Assertions.assertEquals(0, authorDao.findAll().size());
    }
}