package org.example.springproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.springproject.dto.AuthorDto;
import org.example.springproject.models.Author;
import org.example.springproject.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    @GetMapping("/api/v1/authors")
    public List<AuthorDto> findAll(@RequestParam(name = "extended") boolean isExtended) { //работает!
        List<Author> authorList = null;
        if (isExtended) {
           authorList = authorService.findAllWithBooks();
        } else {
            authorList = authorService.findAll();
        }
        return authorList.stream().map(author -> modelMapper.map(author, AuthorDto.class)).toList();
    }

    @PostMapping("/api/v1/authors") //работает!
    public void save(@RequestBody AuthorDto authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        authorService.save(author);
    }

    @GetMapping("/api/v1/authors/{id}") //работает!
    public AuthorDto findAuthorById(@PathVariable(name = "id") long id) {
        return modelMapper.map(authorService.findAuthorById(id), AuthorDto.class);
    }

    @PatchMapping("/api/v1/authors/{id}")  //Работает!
    public void updateAuthorName(@RequestBody AuthorDto authorDto,
                                 @PathVariable(name = "id") long id) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorService.updateAuthorName(author, id);
    }

    @DeleteMapping("/api/v1/authors/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        authorService.delete(id);
    }
}

