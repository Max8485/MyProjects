package org.maxsid.library.core.mapper;

import org.mapstruct.Mapper;
import org.maxsid.library.core.dto.ApplicationUserDto;
import org.maxsid.library.core.entity.ApplicationUser;

@Mapper
public interface ApplicationUserMapper {

    ApplicationUserDto toUserDto(ApplicationUser applicationUser);

    ApplicationUser toUser(ApplicationUserDto applicationUserDto);
}
