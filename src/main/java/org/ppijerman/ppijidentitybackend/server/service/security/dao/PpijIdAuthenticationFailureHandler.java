package org.ppijerman.ppijidentitybackend.server.service.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PpijIdAuthenticationFailureHandler implements AuthenticationFailureHandler  {

    private final IpRateLimiterService ipRateLimiterService;

    @Autowired
    public PpijIdAuthenticationFailureHandler(
            IpRateLimiterService ipRateLimiterService
    ) {
        this.ipRateLimiterService = ipRateLimiterService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        final String ip = request.getRemoteAddr();
        ipRateLimiterService.addTrialErrorToIp(ip);
    }
}
