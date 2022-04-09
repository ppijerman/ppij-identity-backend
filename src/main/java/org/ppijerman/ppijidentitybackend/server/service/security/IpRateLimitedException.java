package org.ppijerman.ppijidentitybackend.server.service.security;

import org.springframework.security.core.AuthenticationException;

public class IpRateLimitedException extends AuthenticationException {
    public IpRateLimitedException(String msg) {
        super(msg);
    }
}
