package org.ppijerman.ppijidentitybackend.server.service.security.dao.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PpijIdAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Logger log = LoggerFactory.getLogger(PpijIdAuthenticationFilter.class);

    IpRateLimiterService ipRateLimiterService;

    @Autowired
    public PpijIdAuthenticationFilter(
            IpRateLimiterService ipRateLimiterService,
            List<AuthenticationProvider> ppijAuthenticationProviderList
    ) {
        this.ipRateLimiterService = ipRateLimiterService;
        this.setAuthenticationManager(new ProviderManager(ppijAuthenticationProviderList));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String ip = request.getRemoteAddr();
        log.debug("Got authentication attempt from ip {}.", ip);

        if (!this.ipRateLimiterService.checkIpIsNotRateLimited(ip)) {
            log.debug("Ip {} is rate limited due to many failed trials.", ip);
            throw new IpRateLimitedException("Sorry, you just tried too many failed attempt to log in. Please try again later.");
        }

        return super.attemptAuthentication(request, response);
    }
}