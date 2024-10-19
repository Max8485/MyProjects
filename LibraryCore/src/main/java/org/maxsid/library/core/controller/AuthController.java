package org.maxsid.library.core.controller;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.core.dto.ApplicationUserDto;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.entity.ApplicationUserAccount;
import org.maxsid.library.core.mapper.ApplicationUserMapper;
import org.maxsid.library.core.service.RegistrationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final ApplicationUserMapper mapper;
    private final RegistrationService registrationService;

    @PostMapping("/api/v1/registration")
    public void registrationPage(@RequestBody ApplicationUserDto userDto) {
        ApplicationUserAccount userAccount = mapper.toUserAccount(userDto);
        ApplicationUser user = mapper.toUser(userDto);
        registrationService.registerUser(user, userAccount);
    }
}
