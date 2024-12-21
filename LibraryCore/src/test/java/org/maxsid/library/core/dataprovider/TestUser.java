package org.maxsid.library.core.dataprovider;

import org.maxsid.library.core.entity.ApplicationUser;

import java.time.LocalDate;

public class TestUser {

    public static ApplicationUser buildUser(int index) {
        return ApplicationUser.builder()
                .firstName("Сидоров" + index)
                .lastName("Максим" + index)
                .middleName("Андреевич" + index)
                .dateOfBirth(LocalDate.now())
                .build();
    }

    public static ApplicationUserDto buildUserDto(int index) {
        return ApplicationUserDto.builder()
//                .password("password" + index)
                .login("login" + index)
                .firstName("Сидоров" + index)
                .lastName("Максим" + index)
                .middleName("Андреевич" + index)
                .dateOfBirth(LocalDate.now())
                .build();
    }
}
