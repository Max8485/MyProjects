package org.maxsid.library.core.dataprovider;

import org.maxsid.library.core.dto.AuthorDto;
import org.maxsid.library.core.dto.BookDto;
import org.maxsid.library.core.dto.BookDtoShort;
import org.maxsid.library.core.entity.Author;
import org.maxsid.library.core.entity.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

public class TestAuthorAndBook {

    public static Author buildAuthor(int index) {
        return Author.builder()
                .firstName("TestFirstName" + index)
                .lastName("TestLastName" + index)
                .middleName("TestMiddleName" + index)
                .dateOfBirth(LocalDate.now())
                .build();
    }

    public static AuthorDto buildAuthorDto(int index) {
        return AuthorDto.builder()
                .firstName("TestFirstName" + index)
                .lastName("TestLastName" + index)
                .middleName("TestMiddleName" + index)
                .dateOfBirth(LocalDate.now())
                .build();
    }

    public static Book buildBook(int index) {
        return Book.builder()
                .title("TestTitle" + index)
                .build();
    }

    public static BookDtoShort buildBookDtoShort(int index) {
        return BookDtoShort.builder()
                .title("TestTitle" + index)
                .build();
    }

    public static BookDto buildBookDto(int index, Long id) {
        return BookDto.builder()
                .id(id)
                .title("TestTitle" + index)
                .build();
    }

    public static List<Author> buildAuthorWithBooks(int countAuthors, int countBooks) {
        return IntStream.range(0, countAuthors)
                .mapToObj(TestAuthorAndBook::buildAuthor)
                .peek(author -> author.setBooks(
                                IntStream.range(0, countBooks).mapToObj(TestAuthorAndBook::buildBook)
                                        .peek(book -> book.setAuthor(author))
                                        .toList()
                        )
                ).toList();
    }
}