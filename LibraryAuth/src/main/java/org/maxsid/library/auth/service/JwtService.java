package org.maxsid.library.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String buildJwtToken(UserDetails userDetails);
}
