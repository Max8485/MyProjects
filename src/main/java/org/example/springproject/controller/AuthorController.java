package org.example.springproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.springproject.dto.AuthorDto;
import org.example.springproject.dto.AuthorDtoShort;
import org.example.springproject.models.Author;
import org.example.springproject.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    @GetMapping("/api/v1/authors")
    public List<AuthorDtoShort> findAll() { //работает!
        return authorService.findAll().stream()
                .map(author -> modelMapper.map(author, AuthorDtoShort.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/authors/extended")  //работает!
    public List<AuthorDto> findAllWithBooks() {
        return authorService.findAllWithBooks().stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/v1/authors") //работает!
    public void save(@RequestBody AuthorDtoShort authorDTOShort) {
        Author author = modelMapper.map(authorDTOShort, Author.class);
        authorService.save(author);
    }

    @GetMapping("/api/v1/authors/{id}") //работает!
    public AuthorDto findAuthorById(@PathVariable(name = "id") long id) {
        return modelMapper.map(authorService.findAuthorById(id), AuthorDto.class);
    }

    @PatchMapping("/api/v1/authors/{id}")  //Работает!
    public void updateAuthorName(@RequestBody AuthorDtoShort authorDtoShort,
                                 @PathVariable(name = "id") long id) {
        Author author = modelMapper.map(authorDtoShort, Author.class);
        authorService.updateAuthorName(author, id);
    }

    @DeleteMapping("/api/v1/authors/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        authorService.delete(id);
    }
}

