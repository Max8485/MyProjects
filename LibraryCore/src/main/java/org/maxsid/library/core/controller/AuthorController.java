package org.maxsid.library.core.controller;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.core.dto.AuthorDto;
import org.maxsid.library.core.entity.Author;
import org.maxsid.library.core.mapper.AuthorDtoMapper;
import org.maxsid.library.core.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorDtoMapper mapper;

    @GetMapping("/api/v1/authors")
    public Page<AuthorDto> findAll(@RequestParam(name = "extended") boolean isExtended,
                                   Pageable pageable) { //
        if (isExtended) {
            return authorService.findAllWithBooks(pageable).map(mapper::toDtoWithBooks);
        } else {
            return authorService.findAll(pageable).map(mapper::toShortDto);
        }
    }

    @PostMapping("/api/v1/authors")
    public AuthorDto save(@RequestBody AuthorDto authorDTO) {
        Author entity = mapper.toEntityAuthor(authorDTO);
        return mapper.toDtoWithBooks(authorService.save(entity));
    }

    @GetMapping("/api/v1/authors/{id}")
    public AuthorDto findAuthorById(@PathVariable(name = "id") long id) {
        return mapper.toDtoWithBooks(authorService.findAuthorById(id));
    }

    @PatchMapping("/api/v1/authors/{id}")
    public void updateAuthor(@RequestBody AuthorDto authorDto,
                             @PathVariable(name = "id") long id) {
        Author entity = mapper.toEntityAuthor(authorDto);
        authorService.updateAuthor(entity, id);

    }

    @DeleteMapping("/api/v1/authors/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        authorService.delete(id);
    }
}

