package org.maxsid.library.auth.controller;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.auth.dto.AuthRequestDto;
import org.maxsid.library.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/v1/auth/token")
    public String getJwtToken(@RequestBody AuthRequestDto authRequestDto) {
        return authService.authenticate(authRequestDto);
    }
}
