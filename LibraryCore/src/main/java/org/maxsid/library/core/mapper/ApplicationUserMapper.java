package org.maxsid.library.core.mapper;

import org.maxsid.library.core.dto.ApplicationUserDto;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.entity.ApplicationUserAccount;
import org.mapstruct.Mapper;

@Mapper
public interface ApplicationUserMapper {

    ApplicationUserDto toUserDto(ApplicationUser applicationUser);

    ApplicationUser toUser(ApplicationUserDto applicationUserDto);

    ApplicationUserAccount toUserAccount(ApplicationUserDto userDto);
}
