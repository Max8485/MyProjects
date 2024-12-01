package org.maxsid.library.auth.service;

import org.maxsid.library.auth.dto.ApplicationUserDto;
import org.maxsid.library.auth.entity.ApplicationUserAccount;

public interface LibraryCoreService {
    void sendToCore(ApplicationUserDto userDto);
}
