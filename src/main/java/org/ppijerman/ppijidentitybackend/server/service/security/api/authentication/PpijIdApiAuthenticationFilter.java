package org.ppijerman.ppijidentitybackend.server.service.security.api.authentication;

import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PpijIdApiAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public PpijIdApiAuthenticationFilter(
            RequestMatcher requiresAuthenticationRequestMatcher
    ) {
        super(requiresAuthenticationRequestMatcher);
        this.setAuthenticationManager(new ProviderManager());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
