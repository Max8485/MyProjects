package org.maxsid.library.auth.service.impl;

import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.maxsid.library.auth.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.expirationInMinutes}")
    private int expirationInMinutes;

    @Override
    public String buildJwtToken(UserDetails userDetails) {
        Date creationDate = clock.now();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .setIssuedAt(creationDate)
                .setExpiration(new Date(creationDate.getTime() + expirationInMinutes * 1000 * 60))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
