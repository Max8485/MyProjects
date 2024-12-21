package org.maxsid.library.auth.service;

import org.maxsid.library.dto.ApplicationUserDto;

public interface RegistrationService {

    void registerUser(ApplicationUserDto userDto);
}
