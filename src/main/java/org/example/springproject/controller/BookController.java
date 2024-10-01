package org.example.springproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.springproject.dto.BookDto;
import org.example.springproject.dto.BookDtoShort;
import org.example.springproject.entity.Book;
import org.example.springproject.mapper.BookDtoMapper;
import org.example.springproject.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookDtoMapper mapper;

    @GetMapping("/api/v1/books/{id}") //работает!
    public BookDto findBookById(@PathVariable(name = "id") long id) {  //и книгу и автора выводить должен!
        return mapper.toBookDto(bookService.findBookById(id));
    }

    @PostMapping("/api/v1/author/{authorId}/book") //сохраняет!
    public void save(@PathVariable(name = "authorId") long authorId,
                     @RequestBody BookDtoShort bookDtoShort) {
        Book entity = mapper.toEntity(bookDtoShort);
        bookService.save(entity, authorId);
    }

    @PatchMapping("/api/v1/books/{id}") //обновляет!
    public void updateBookTitle(
            @PathVariable(name = "id") long id,
            @RequestBody BookDto bookDto) {
        Book entity2 = mapper.toEntity2(bookDto);
        bookService.updateBook(entity2, id);
    }

    @DeleteMapping("/api/v1/books/{id}") //работает!
    public void delete(@PathVariable(name = "id") long id) {
        bookService.delete(id);
    }
}

