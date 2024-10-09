package org.example.springproject.mapper;

import org.example.springproject.dataprovider.TestAuthorAndBook;
import org.example.springproject.dto.AuthorDto;
import org.example.springproject.dto.BookDto;
import org.example.springproject.dto.BookDtoShort;
import org.example.springproject.entity.Author;
import org.example.springproject.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class BookDtoMapperTest {

    @Test
    void toBookDto() {
        BookDtoMapper mapper = Mappers.getMapper(BookDtoMapper.class);
        Book book = TestAuthorAndBook.buildBook(1);
        BookDto bookDto = mapper.toBookDto(book);

        Assertions.assertEquals(book.getTitle(), bookDto.getTitle());
    }

    @Test
    void toEntity() {
        AuthorDtoMapper mapperAuthor = Mappers.getMapper(AuthorDtoMapper.class);
        AuthorDto authorDto = TestAuthorAndBook.buildAuthorDto(1);
        Author author = mapperAuthor.toEntityAuthor(authorDto);

        BookDtoMapper mapper = Mappers.getMapper(BookDtoMapper.class);
        BookDtoShort bookDtoShort = TestAuthorAndBook.buildBookDtoShort(1);
        bookDtoShort.setAuthor(authorDto);
        Book book = mapper.toEntity(bookDtoShort);
        book.setAuthor(author);

        Assertions.assertEquals(bookDtoShort.getAuthor(), book.getAuthor()); //authorDto author
        //assertNotNull
        Assertions.assertEquals(bookDtoShort.getTitle(), book.getTitle());
    }

    @Test
    void toEntity2() { //работает!
        BookDtoMapper mapper = Mappers.getMapper(BookDtoMapper.class);
        BookDto bookDto = TestAuthorAndBook.buildBookDto(1, 1L);

        Book book = mapper.toEntity2(bookDto);

        Assertions.assertEquals(bookDto.getId(), book.getId());
        Assertions.assertEquals(bookDto.getTitle(), book.getTitle());
    }
}