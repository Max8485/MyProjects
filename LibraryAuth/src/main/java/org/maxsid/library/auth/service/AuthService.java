package org.maxsid.library.auth.service;

import org.maxsid.library.auth.dto.AuthRequestDto;

public interface AuthService {
    String authenticate(AuthRequestDto authRequestDto);
}
