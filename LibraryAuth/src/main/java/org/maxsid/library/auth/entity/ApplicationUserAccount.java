package org.maxsid.library.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "account_non_expired")
    private boolean isAccountNonExpired = true;

    @Column(name = "account_non_locked")
    private boolean isAccountNonLocked = true;

    @Column(name = "credentials_non_expired")
    private boolean isCredentialsNonExpired = true;

    @Column(name = "enabled")
    private boolean isEnabled = true;

    @ManyToMany(fetch = FetchType.EAGER) //исправить на LAZY и написать кастомный запрос в репозиторий.
    @JoinTable(name = "application_user_role_account_binding",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return login;
    }
}
