package org.maxsid.library.core.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.maxsid.library.core.entity.ApplicationUser;
import org.maxsid.library.core.entity.ApplicationUserAccount;
import org.maxsid.library.core.repository.ApplicationUserAccountRepository;
import org.maxsid.library.core.repository.ApplicationUserRepository;
import org.maxsid.library.core.service.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final ApplicationUserRepository userRepository;
    private final ApplicationUserAccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void registerUser(ApplicationUser user, ApplicationUserAccount userAccount) {
//         userRepository.save(user);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setApplicationUser(user);
        accountRepository.save(userAccount);
    }
}
