package org.maxsid.library.core.repository;

import org.maxsid.library.core.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query(nativeQuery = true, value = "select exists(select 1 from application_user where login=:login)")
    boolean existsByLogin(String login);
}
