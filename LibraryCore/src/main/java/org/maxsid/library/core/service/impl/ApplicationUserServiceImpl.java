package org.maxsid.library.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.repository.ApplicationUserRepository;
import org.maxsid.library.core.service.ApplicationUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository userRepository;

    @Override
    public void saveUser(ApplicationUser user) {
        if (!userRepository.existsByLogin(user.getLogin())) {
            userRepository.save(user);
        } else {
            log.warn("Catch duplicate {}", user.getLogin());
        }
    }
}
