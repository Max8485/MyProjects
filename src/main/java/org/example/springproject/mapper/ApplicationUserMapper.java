package org.example.springproject.mapper;

import org.example.springproject.dto.ApplicationUserDto;
import org.example.springproject.entity.ApplicationUser;
import org.example.springproject.entity.ApplicationUserAccount;
import org.mapstruct.Mapper;

@Mapper
public interface ApplicationUserMapper {

    ApplicationUserDto toUserDto(ApplicationUser applicationUser);

    ApplicationUser toUser(ApplicationUserDto applicationUserDto);

    ApplicationUserAccount toUserAccount(ApplicationUserDto userDto);
}
