package org.example.springproject.mapper;

import org.example.springproject.dto.BookDto;
import org.example.springproject.dto.BookDtoShort;
import org.example.springproject.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface BookDtoMapper {

    BookDto toBookDto(Book book);


    Book toEntity(BookDtoShort bookDtoShort);

    Book toEntity2(BookDto bookDto);
}
