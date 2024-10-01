package org.example.springproject.mapper;

import org.example.springproject.dto.AuthorDto;
import org.example.springproject.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface AuthorDtoMapper {

    AuthorDto toShortDto(Author author);

    @Mappings({
            @Mapping(target = "booksList", source = "books")
    })
    AuthorDto toDtoWithBooks(Author author);


    Author toEntityAuthor(AuthorDto authorDto);
}
