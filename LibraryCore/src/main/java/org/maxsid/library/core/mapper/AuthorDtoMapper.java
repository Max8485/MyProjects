package org.maxsid.library.core.mapper;

import org.maxsid.library.core.dto.AuthorDto;
import org.maxsid.library.core.entity.Author;
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
