package org.maxsid.library.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.auth.dto.AuthRequestDto;
import org.maxsid.library.auth.service.AuthService;
import org.maxsid.library.auth.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public String authenticate(AuthRequestDto authRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getLogin(), authRequestDto.getPassword()));
        return jwtService.buildJwtToken(userDetailsService.loadUserByUsername(authRequestDto.getLogin()));
    }
}
