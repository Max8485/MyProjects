package org.maxsid.library.auth.repository;


import org.maxsid.library.auth.entity.ApplicationUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserAccountRepository extends JpaRepository<ApplicationUserAccount, Long> {
    ApplicationUserAccount findByLogin(String username);
}
