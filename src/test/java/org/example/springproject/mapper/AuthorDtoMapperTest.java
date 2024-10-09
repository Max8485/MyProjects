package org.example.springproject.mapper;

import org.example.springproject.dataprovider.TestAuthorAndBook;
import org.example.springproject.dto.AuthorDto;
import org.example.springproject.entity.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

class AuthorDtoMapperTest {
    private final AuthorDtoMapper mapper = Mappers.getMapper(AuthorDtoMapper.class);

    @Test
    void toShortDto() { //работает!
        Author author = TestAuthorAndBook.buildAuthor(1);
        AuthorDto authorShortDto = mapper.toShortDto(author);

        Assertions.assertEquals(author.getId(), authorShortDto.getId());
        Assertions.assertEquals(author.getFirstName(), authorShortDto.getFirstName());
        Assertions.assertEquals(author.getLastName(), authorShortDto.getLastName());
        Assertions.assertEquals(author.getMiddleName(), authorShortDto.getMiddleName());
        Assertions.assertEquals(author.getDateOfBirth(), authorShortDto.getDateOfBirth());
    }

    @Test
    void toDtoWithBooks() { //работает!
        List<Author> authorList = TestAuthorAndBook.buildAuthorWithBooks(1, 1);
        Author author = authorList.get(0);
        AuthorDto authorDtoWithBooks = mapper.toDtoWithBooks(author);

        Assertions.assertEquals(author.getId(), authorDtoWithBooks.getId());
        Assertions.assertEquals(author.getFirstName(), authorDtoWithBooks.getFirstName());
        Assertions.assertEquals(author.getLastName(), authorDtoWithBooks.getLastName());
        Assertions.assertEquals(author.getMiddleName(), authorDtoWithBooks.getMiddleName());
        Assertions.assertEquals(author.getDateOfBirth(), authorDtoWithBooks.getDateOfBirth());
        Assertions.assertEquals(author.getBooks().size(), authorDtoWithBooks.getBooksList().size());
        authorDtoWithBooks.getBooksList().forEach(bookDto -> {
            Assertions.assertNotNull(bookDto.getTitle());
        });
    }

    @Test
    void toEntityAuthor() { //работает!
        AuthorDto authorDto = TestAuthorAndBook.buildAuthorDto(1);
        Author author = mapper.toEntityAuthor(authorDto);

        Assertions.assertEquals(authorDto.getFirstName(), author.getFirstName());
        Assertions.assertEquals(authorDto.getLastName(), author.getLastName());
        Assertions.assertEquals(authorDto.getMiddleName(), author.getMiddleName());
        Assertions.assertEquals(authorDto.getDateOfBirth(), author.getDateOfBirth());
    }
}