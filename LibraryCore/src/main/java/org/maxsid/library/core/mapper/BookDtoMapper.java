package org.maxsid.library.core.mapper;

import org.maxsid.library.core.dto.BookDto;
import org.maxsid.library.core.dto.BookDtoShort;
import org.maxsid.library.core.entity.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookDtoMapper {

    BookDto toBookDto(Book book);


    Book toEntity(BookDtoShort bookDtoShort);

    Book toEntity2(BookDto bookDto);
}
