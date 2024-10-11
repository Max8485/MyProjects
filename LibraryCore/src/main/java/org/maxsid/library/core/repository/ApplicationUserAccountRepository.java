package org.maxsid.library.core.repository;

import org.maxsid.library.core.entity.ApplicationUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserAccountRepository extends JpaRepository<ApplicationUserAccount, Long> {
    ApplicationUserAccount findByLogin(String username);
}
