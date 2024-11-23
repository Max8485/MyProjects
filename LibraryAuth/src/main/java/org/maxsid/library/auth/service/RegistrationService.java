package org.maxsid.library.auth.service;

import org.maxsid.library.auth.entity.ApplicationUserAccount;

public interface RegistrationService {

    void registerUser(ApplicationUserAccount userAccount);
}
