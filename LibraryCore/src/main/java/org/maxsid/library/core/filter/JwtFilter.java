package org.maxsid.library.core.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer ";

    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Strings.isEmpty(headerValue) || !headerValue.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = headerValue.replace(TOKEN_PREFIX, "");

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt);

            Claims body = claimsJws.getBody();
            String subject = body.getSubject();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(subject, "", resolveRole(body));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Токен не действителен: %s", jwt));
        }
    }

    private Set<SimpleGrantedAuthority> resolveRole(Claims body) {
        List<Map<String, String>> roles = (List) body.get("roles");
        Set<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.get("authority")))
                .collect(Collectors.toSet());

        return authorities;
    }
}
