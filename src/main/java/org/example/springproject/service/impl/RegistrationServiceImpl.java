package org.example.springproject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springproject.entity.ApplicationUser;
import org.example.springproject.entity.ApplicationUserAccount;
import org.example.springproject.repository.ApplicationUserAccountRepository;
import org.example.springproject.repository.ApplicationUserRepository;
import org.example.springproject.service.RegistrationService;
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
