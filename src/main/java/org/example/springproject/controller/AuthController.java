package org.example.springproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.springproject.dto.ApplicationUserDto;
import org.example.springproject.entity.ApplicationUser;
import org.example.springproject.entity.ApplicationUserAccount;
import org.example.springproject.mapper.ApplicationUserMapper;
import org.example.springproject.service.RegistrationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
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
