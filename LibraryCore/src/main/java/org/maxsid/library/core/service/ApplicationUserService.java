package org.maxsid.library.core.service;

import org.maxsid.library.core.entity.ApplicationUser;

public interface ApplicationUserService {

    void saveUser(ApplicationUser user);

    void takeBook(String login, Long bookId);

    void returnBook(Long bookId);
}
