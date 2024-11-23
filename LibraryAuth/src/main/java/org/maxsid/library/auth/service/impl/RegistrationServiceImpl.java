package org.maxsid.library.auth.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.maxsid.library.auth.entity.ApplicationUserAccount;
import org.maxsid.library.auth.repository.ApplicationUserAccountRepository;
import org.maxsid.library.auth.service.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final ApplicationUserAccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void registerUser(ApplicationUserAccount userAccount) {
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        accountRepository.save(userAccount);
    }
}
