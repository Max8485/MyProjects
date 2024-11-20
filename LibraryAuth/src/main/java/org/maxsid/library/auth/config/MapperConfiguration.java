package org.maxsid.library.auth.config;

import org.mapstruct.factory.Mappers;
import org.maxsid.library.auth.mapper.ApplicationUserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public ApplicationUserMapper applicationUserMapper() {
        return Mappers.getMapper(ApplicationUserMapper.class);
    }
}
