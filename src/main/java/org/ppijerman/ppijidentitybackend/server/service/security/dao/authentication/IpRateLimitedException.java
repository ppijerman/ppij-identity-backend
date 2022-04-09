package org.ppijerman.ppijidentitybackend.server.service.security.dao.authentication;

import org.springframework.security.core.AuthenticationException;

public class IpRateLimitedException extends AuthenticationException {
    public IpRateLimitedException(String msg) {
        super(msg);
    }
}
