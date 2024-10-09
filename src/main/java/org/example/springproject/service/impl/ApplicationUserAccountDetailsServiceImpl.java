package org.example.springproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springproject.repository.ApplicationUserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserAccountDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByLogin(username);
    }

}
