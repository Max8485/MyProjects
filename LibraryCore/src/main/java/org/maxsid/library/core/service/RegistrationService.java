package org.maxsid.library.core.service;

import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.entity.ApplicationUserAccount;

public interface RegistrationService {

    void registerUser(ApplicationUser applicationUser, ApplicationUserAccount userAccount);

}
