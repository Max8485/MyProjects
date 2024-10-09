package org.example.springproject.repository;

import org.example.springproject.entity.ApplicationUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserAccountRepository extends JpaRepository<ApplicationUserAccount, Long> {
    ApplicationUserAccount findByLogin(String username);
}
