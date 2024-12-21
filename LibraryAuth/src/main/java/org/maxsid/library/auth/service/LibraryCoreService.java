package org.maxsid.library.auth.service;

import org.maxsid.library.dto.ApplicationUserDto;

public interface LibraryCoreService {
    void sendToCore(ApplicationUserDto userDto);
}
