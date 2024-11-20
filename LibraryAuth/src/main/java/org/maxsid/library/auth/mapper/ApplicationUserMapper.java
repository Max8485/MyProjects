package org.maxsid.library.auth.mapper;


import org.mapstruct.Mapper;
import org.maxsid.library.auth.dto.ApplicationUserDto;
import org.maxsid.library.auth.entity.ApplicationUser;
import org.maxsid.library.auth.entity.ApplicationUserAccount;

@Mapper
public interface ApplicationUserMapper {

    ApplicationUserDto toUserDto(ApplicationUser applicationUser);

    ApplicationUser toUser(ApplicationUserDto applicationUserDto);

    ApplicationUserAccount toUserAccount(ApplicationUserDto userDto);
}
