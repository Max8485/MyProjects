package org.maxsid.library.core.repository;

import org.maxsid.library.core.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query(nativeQuery = true, value = "select exists(select 1 from application_user where login=:login)")
    boolean existsByLogin(String login);

    Optional<ApplicationUser> findByLogin(String login);

    @Query(value = "SELECT user.id from ApplicationUser user where user.login=:login")
    Optional<Long> findUserIdByLogin(String login);
}
