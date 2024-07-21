package org.example.springproject.controller;

import org.example.springproject.dto.BookDto;
import org.example.springproject.models.Book;
import org.example.springproject.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/v1/books")
    public List<BookDto> findAll() { //работает!
        return bookService.findAll().stream().map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/books/{id}")
    public BookDto findBookById(@PathVariable(name = "id") long id) {
        return modelMapper.map(bookService.findBookById(id), BookDto.class);

    }

//    @GetMapping("/api/v1/books/{id}")
//    public List<Book> findBooksByAuthorId(@PathVariable(name = "id") long id) {
//        return null;
//    }

    @PostMapping("/api/v1/books")  //РАБОТАЕТ!
    public void save(@RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookService.save(book);
    }

    @PatchMapping("/api/v1/books/{id}")  //работает!
    public void updateBookTitle(@PathVariable(name = "id") long id,
                               @RequestBody BookDto bookDTO) {

        Book book = modelMapper.map(bookDTO, Book.class);
        bookService.updateBookTitle(book, id);
    }

    @DeleteMapping("/api/v1/books/{id}") //работает!
    public void delete(@PathVariable(name = "id") long id) {
        bookService.delete(id);
    }
}
