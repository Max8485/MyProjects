package org.maxsid.library.auth.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.maxsid.library.auth.entity.ApplicationUserAccount;
import org.maxsid.library.auth.mapper.ApplicationUserMapper;
import org.maxsid.library.auth.repository.ApplicationUserAccountRepository;
import org.maxsid.library.auth.service.LibraryCoreService;
import org.maxsid.library.auth.service.RegistrationService;
import org.maxsid.library.dto.ApplicationUserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final ApplicationUserAccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final LibraryCoreService libraryCoreService;
    private final ApplicationUserMapper mapper;

    @Transactional
    @Override
    public void registerUser(ApplicationUserDto userDto) {
        ApplicationUserAccount userAccount = mapper.toUserAccount(userDto);

        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        accountRepository.save(userAccount);
        userDto.setPassword(null);
        libraryCoreService.sendToCore(userDto);
    }
}
