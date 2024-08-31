package org.example.springproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.springproject.dto.AuthorDto;
import org.example.springproject.entity.Author;
import org.example.springproject.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    @GetMapping("/api/v1/authors")
    public Page<AuthorDto> findAll(@RequestParam(name = "extended") boolean isExtended,
                                   Pageable pageable) { //
        Page<Author> authorPage = null;
        if (isExtended) {
            authorPage = authorService.findAllWithBooks(pageable);
        } else {
            authorPage = authorService.findAll(pageable);
        }
        return authorPage.map(author -> modelMapper.map(author, AuthorDto.class));
    }

    @PostMapping("/api/v1/authors") //работает!
    public AuthorDto save(@RequestBody AuthorDto authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        return modelMapper.map(authorService.save(author), AuthorDto.class);
    }

    @GetMapping("/api/v1/authors/{id}") //работает!
    public AuthorDto findAuthorById(@PathVariable(name = "id") long id) {
        Author author = authorService.findAuthorById(id);
        return modelMapper.map(author, AuthorDto.class);
    }

    @PatchMapping("/api/v1/authors/{id}")  //Работает!
    public void updateAuthor(@RequestBody AuthorDto authorDto,
                             @PathVariable(name = "id") long id) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorService.updateAuthor(author, id);
    }

    @DeleteMapping("/api/v1/authors/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        authorService.delete(id);
    }
}

