package org.maxsid.library.auth.controller;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.auth.dto.ApplicationUserDto;
import org.maxsid.library.auth.dto.AuthRequestDto;
import org.maxsid.library.auth.service.AuthService;
import org.maxsid.library.auth.service.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RegistrationService registrationService;

    @PostMapping("/api/v1/registration")
    public void registrationPage(@RequestBody ApplicationUserDto userDto) {
        registrationService.registerUser(userDto);
    }

    @PostMapping("/api/v1/auth/token")
    public String getJwtToken(@RequestBody AuthRequestDto authRequestDto) {
        return authService.authenticate(authRequestDto);
    }
}
