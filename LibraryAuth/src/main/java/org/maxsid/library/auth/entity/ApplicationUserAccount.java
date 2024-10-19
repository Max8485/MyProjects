package org.maxsid.library.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application_user_account")
public class ApplicationUserAccount implements UserDetails {

    @Id
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @Column(name = "enabled")
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public String getUsername() {
        return login;
    }
}
