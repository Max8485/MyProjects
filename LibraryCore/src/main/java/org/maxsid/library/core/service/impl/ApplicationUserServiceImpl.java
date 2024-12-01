package org.maxsid.library.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.repository.ApplicationUserRepository;
import org.maxsid.library.core.service.ApplicationUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository userRepository;
    @Override
    public void saveUser(ApplicationUser user) {
        userRepository.save(user);
    }
}
