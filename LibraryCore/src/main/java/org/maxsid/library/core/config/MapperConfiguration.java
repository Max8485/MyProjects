package org.maxsid.library.core.config;

import org.maxsid.library.core.mapper.ApplicationUserMapper;
import org.maxsid.library.core.mapper.AuthorDtoMapper;
import org.maxsid.library.core.mapper.BookDtoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public AuthorDtoMapper authorDtoMapper() {
        return Mappers.getMapper(AuthorDtoMapper.class);
    }

    @Bean
    public BookDtoMapper bookDtoMapper() {
        return Mappers.getMapper(BookDtoMapper.class);
    }

    @Bean
    public ApplicationUserMapper applicationUserMapper() {
        return Mappers.getMapper(ApplicationUserMapper.class);
    }
}
