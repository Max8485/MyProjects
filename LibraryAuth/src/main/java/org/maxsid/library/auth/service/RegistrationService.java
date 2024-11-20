package org.maxsid.library.auth.service;

import org.maxsid.library.auth.entity.ApplicationUser;
import org.maxsid.library.auth.entity.ApplicationUserAccount;

public interface RegistrationService {

    void registerUser(ApplicationUser applicationUser, ApplicationUserAccount userAccount);
}
