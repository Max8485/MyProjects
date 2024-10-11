package org.maxsid.library.core.mapper;

import org.maxsid.library.core.dataprovider.TestUser;
import org.maxsid.library.core.dto.ApplicationUserDto;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.entity.ApplicationUserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ApplicationUserMapperTest {

    private final ApplicationUserMapper mapper = Mappers.getMapper(ApplicationUserMapper.class);

    @Test
    void toUserDto() {
        ApplicationUser user = TestUser.buildUser(1);
        ApplicationUserDto userDto = mapper.toUserDto(user);

        Assertions.assertEquals(user.getFirstName(), userDto.getFirstName());
    }

    @Test
    void toUserAndToAccount() {
        ApplicationUserDto userDto = TestUser.buildUserDto(1);
        ApplicationUser user = mapper.toUser(userDto);
        ApplicationUserAccount userAccount = mapper.toUserAccount(userDto);

        Assertions.assertNotNull(userAccount.getLogin());
        Assertions.assertNotNull(userAccount.getPassword());

        Assertions.assertNotNull(user.getFirstName());
        Assertions.assertNotNull(user.getLastName());
        Assertions.assertNotNull(user.getMiddleName());
        Assertions.assertNotNull(user.getDateOfBirth());
    }

//    @Test
//    void toUserAccountDto() {
//        ApplicationUserAccount userAccount = TestUser.buildUserAccount(1);
//        ApplicationUserAccountDto userAccountDto = mapper.toUserAccountDto(userAccount);
//
//        Assertions.assertEquals(userAccount.getPassword(), userAccountDto.getPassword());
//        Assertions.assertEquals(userAccount.getLogin(), userAccountDto.getLogin());
//    }

//    @Test
//    void toUserAccount() {
//        ApplicationUserAccountDto userAccountDto = TestUser.buildUserAccountDto(1);
//        ApplicationUserAccount userAccount = mapper.toUserAccount(userAccountDto);
//
//        Assertions.assertEquals(userAccountDto.getPassword(), userAccount.getPassword());
//        Assertions.assertEquals(userAccountDto.getLogin(), userAccount.getLogin());
//    }
}