package org.example.springproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.springproject.dto.BookDto;
import org.example.springproject.dto.BookDtoShort;
import org.example.springproject.entity.Book;
import org.example.springproject.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    @GetMapping("/api/v1/books/{id}")
    public BookDto findBookById(@PathVariable(name = "id") long id) {
        return modelMapper.map(bookService.findBookById(id), BookDto.class);

    }

    @PostMapping("/api/v1/author/{authorId}/book")
    public void save(@PathVariable(name = "authorId") long authorId,
                     @RequestBody BookDtoShort bookDtoShort) {
        Book book = modelMapper.map(bookDtoShort, Book.class);
        bookService.save(book, authorId);
    }

    @PatchMapping("/api/v1/books/{id}")
    public void updateBookTitle(
            @PathVariable(name = "id") long id,
            @RequestBody BookDto bookDto) {

        Book book = modelMapper.map(bookDto, Book.class);
        bookService.updateBook(book, id);
    }

    @DeleteMapping("/api/v1/books/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        bookService.delete(id);
    }
}

