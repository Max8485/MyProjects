package org.maxsid.library.auth.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.maxsid.library.auth.dto.ApplicationUserDto;
import org.maxsid.library.auth.entity.ApplicationUserAccount;

@Mapper(builder = @Builder(disableBuilder = true))
public interface ApplicationUserMapper {

    ApplicationUserAccount toUserAccount(ApplicationUserDto userDto);
}
