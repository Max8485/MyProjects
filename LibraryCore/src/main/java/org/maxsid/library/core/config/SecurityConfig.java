package org.maxsid.library.core.config;

import org.maxsid.library.core.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwt.key}")
    private String key;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new JwtFilter(key), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/v1/authors").hasRole("GUEST")
                        .requestMatchers("/api/v1/authors/{id}").hasRole("GUEST")
                        .requestMatchers("/api/v1/books/{id}").hasRole("GUEST")
                        .requestMatchers("/api/v1/account").permitAll() //работает
                        .requestMatchers("/**").hasRole("ADMIN") //переместили сюда и заработало! Видимо, /** должна быть в конце.
                        .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() { //работает!
        return new BCryptPasswordEncoder();
    }
}
