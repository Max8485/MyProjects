package org.maxsid.library.core.mapper;

import org.mapstruct.Mapper;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.dto.ApplicationUserDto;

@Mapper
public interface ApplicationUserMapper {

    ApplicationUserDto toUserDto(ApplicationUser applicationUser);

    ApplicationUser toUser(ApplicationUserDto applicationUserDto);
}
