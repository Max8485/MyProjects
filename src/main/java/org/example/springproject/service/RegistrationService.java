package org.example.springproject.service;

import org.example.springproject.entity.ApplicationUser;
import org.example.springproject.entity.ApplicationUserAccount;

public interface RegistrationService {

    void registerUser(ApplicationUser applicationUser, ApplicationUserAccount userAccount);

}
